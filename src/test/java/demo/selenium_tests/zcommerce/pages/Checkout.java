package demo.selenium_tests.zcommerce.pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import demo.selenium_tests.wrappers.WebActionWrappers;

public class Checkout {
    
    public enum paymentMethod{
        CASH_ON_DELIVERY, UPI, CREDIT_DEBIT
    }

    WebActionWrappers wrap;
    HashMap<String, String> address;
    HashMap<String, String> cardDetails;
    
    public static HashMap<String, String> loadHashMapFromJson(String filename) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, type);
        }
    }

    @FindBy(xpath = "//button[text()='Proceed to buy']")
    WebElement proceedToBuyButton;

    @FindBy(xpath = "//button[text()='Order Now']")
    WebElement orderNowButton;

    @FindBy(xpath = "//span[text()='Delivery Address']/ancestor::button[1]")
    WebElement deliveryAddressDropdown;

    @FindBy(xpath = "//input[@name='fullAddress']")
    WebElement fullAddressInput;

    @FindBy(xpath = "//input[@name='pinCode']")
    WebElement pinCodeInput;

    @FindBy(xpath = "//input[@name='city']")
    WebElement cityInput;

    @FindBy(xpath = "//input[@name='state']")
    WebElement stateInput;

    @FindBy(xpath = "//input[@name='country']")
    WebElement countryInput;

    @FindBy(xpath = "//button[text()='Save Address']")
    WebElement saveAddressButton;

    @FindBy(xpath = "//span[text()='Review Products']/ancestor::button[1]")
    WebElement reviewProductsDropdown;

    @FindBy(xpath = "//div[@class='font-semibold px-3 py-2 text-xs']")
    List<WebElement> itemCard;

    private final By addMoreOfItem = new By.ByXPath("(//button[@class='chakra-button css-18bkqka'])[2]");
    private final By addLessOfItem = new By.ByXPath("(//button[@class='chakra-button css-18bkqka'])[1]");
    private final By deleteItem = new By.ByXPath("//div[@class='text-center text-emerald-600 text-lg font-black']");
    private final By itemQuantity = new By.ByXPath("//div[@class='flex-1 w-[40px] text-center']");

    @FindBy(xpath = "//span[text()='Payment Method']/ancestor::button[1]")
    WebElement paymentMethodDropdown;

    @FindBy(xpath = "//button[@class='chakra-accordion__button css-1gxevz4' and contains(.,'Cash On Delivery')]")
    WebElement cashOnDeliveryButton;

    @FindBy(xpath = "//button[@class='chakra-accordion__button css-1gxevz4' and contains(.,'UPI')]")
    WebElement upiButton;

    @FindBy(xpath = "//button[@class='chakra-accordion__button css-1gxevz4' and contains(.,'Credit/Debit Card')]")
    WebElement creditOrDebitCardButton;

    @FindBy(xpath = "//input[@name='cardNumber']")
    WebElement cardNumberInput;

    @FindBy(xpath = "//input[@name='expiryDate']")
    WebElement expiryDateInput;

    @FindBy(xpath = "//input[@name='cvv']")
    WebElement cvvInput;

    @FindBy(xpath = "//input[@name='cardHolderName']")
    WebElement cardHolderNameInput;

    @FindBy(xpath = "//button[text()='Add Card']")
    WebElement addCardButton;

    public Checkout(WebActionWrappers wrap) throws FileNotFoundException, IOException{
        this.wrap = wrap;
        PageFactory.initElements(new AjaxElementLocatorFactory(wrap.getDriver(), 10), this);
        address = loadHashMapFromJson(String.format("%s/src/test/resources/address-valid.json",System.getProperty("user.dir")));
        cardDetails = loadHashMapFromJson(String.format("%s/src/test/resources/card-valid.json",System.getProperty("user.dir")));
    }

    public void goToCheckoutPage() throws IOException{
        wrap.get("https://zcommerce.crio.do/cart");
    }

    public void initiateCheckout() throws InterruptedException, IOException{
        wrap.click(proceedToBuyButton);
    }

    public void fillDeliveryAddress() throws InterruptedException, IOException{
        wrap.click(deliveryAddressDropdown);
        wrap.sendKeys(fullAddressInput, address.get("fullAddressInput"));
        wrap.sendKeys(pinCodeInput, address.get("pinCodeInput"));
        wrap.sendKeys(cityInput, address.get("cityInput"));
        wrap.sendKeys(stateInput, address.get("stateInput"));
        wrap.sendKeys(countryInput, address.get("countryInput"));
        wrap.click(saveAddressButton);
    }

    public void reviewProducts(Integer index, Integer quantitiy) throws InterruptedException, IOException{
        wrap.click(reviewProductsDropdown);
        try{
            Integer actualQuantity = Integer.parseInt(itemCard.get(index).findElement(itemQuantity).getText().trim());
            while (actualQuantity!=quantitiy) {
                if(actualQuantity<quantitiy){
                    wrap.click(itemCard.get(index).findElement(addMoreOfItem));
                }
                else{
                    wrap.click(itemCard.get(index).findElement(addLessOfItem));
                }
                // update actualQauntity
                actualQuantity = Integer.parseInt(itemCard.get(index).findElement(itemQuantity).getText().trim());
            }
        }
        catch(IndexOutOfBoundsException e){
            return;
        }
    }

    public void deleteProducts(List<Integer> index) throws InterruptedException, IOException{
        for(Integer i: index){
            wrap.click(itemCard.get(i).findElement(deleteItem));
        }
    }

    public void selectPaymentDetails(paymentMethod p) throws InterruptedException, IOException{
        wrap.click(paymentMethodDropdown);
        switch (p) {
            case CASH_ON_DELIVERY:
                wrap.click(cashOnDeliveryButton);
                break;
            
            case UPI:
                wrap.click(upiButton);
                break;

            case CREDIT_DEBIT:
                wrap.click(creditOrDebitCardButton);
                wrap.sendKeys(cardNumberInput, cardDetails.get("cardNumber"));
                wrap.sendKeys(expiryDateInput, cardDetails.get("expiryDate"));
                wrap.sendKeys(cvvInput, cardDetails.get("cvv"));
                wrap.sendKeys(cardHolderNameInput, cardDetails.get("cardHolderName"));
                wrap.click(addCardButton);
                break;

            default:
                return;
        }
    }

    public void orderNow() throws InterruptedException, IOException{
        wrap.click(orderNowButton);
    }
}
