package demo.selenium_tests.zcommerce;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import demo.selenium_tests.wrappers.WebActionWrappers;
import demo.selenium_tests.zcommerce.pages.Checkout;
import demo.selenium_tests.zcommerce.pages.Home;
import demo.selenium_tests.zcommerce.pages.Login;
import demo.selenium_tests.zcommerce.pages.Register;

public class TestCase01 {

    private WebActionWrappers wrap;

    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String LOWER_LETTERS = "abcdefghijklmnopqrstuvwxyz";


    private String credString = generateRandomEmail();

    public static String generateRandomEmail() {
        Random random = new Random();
        StringBuilder email = new StringBuilder(10);

        // Ensure at least one capital letter and one number
        email.append(CAPITAL_LETTERS.charAt(random.nextInt(CAPITAL_LETTERS.length())));
        email.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));

        // Remaining characters can be any alphanumeric (total 8 characters because 2 are already added)
        String allChars = CAPITAL_LETTERS + LOWER_LETTERS + NUMBERS;
        for (int i = 2; i < 8; i++) {
            email.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle to avoid predictable placement of capital letter and number
        char[] chars = email.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }

        // Append the dummy domain
        return new String(chars) + "@emaildummy.com";
    }

    @Test
    public void goToWebsite() throws IOException, InterruptedException{
        wrap = new WebActionWrappers("Going To Website");
        Home home = new Home(wrap);
        home.goToHomePage();
        Login login = home.clickOnLogin();
        Thread.sleep(1000);
        Register register = login.goToRegister();
        Thread.sleep(1000);
        home = register.tryRegister(credString, credString, credString);
        Thread.sleep(1000);
        home.searchForProduct("aurora");
        home.addProductToCard("aurora rattlesnake");
        Checkout checkout = home.proceedToCheckout();
        Thread.sleep(1000);
        checkout.initiateCheckout();
        checkout.fillDeliveryAddress();
        checkout.reviewProducts(0, 3);
        checkout.selectPaymentDetails(Checkout.paymentMethod.CASH_ON_DELIVERY);
        checkout.orderNow();
        Thread.sleep(1000);
    }

    @AfterClass
    public void tearDown() throws IOException{
        WebActionWrappers wrap = new WebActionWrappers("Exiting driver");
        wrap.tearDown();
    }
}
