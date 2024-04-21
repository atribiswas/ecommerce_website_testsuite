package demo.selenium_tests.zcommerce;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import demo.selenium_tests.wrappers.WebActionWrappers;
import demo.selenium_tests.zcommerce.pages.Home;

public class TestCase01 {

    private WebActionWrappers wrap;

    @Test
    public void goToWebsite() throws IOException, InterruptedException{
        wrap = new WebActionWrappers("Going To Website");
        Home home = new Home(wrap);
        home.goToHomePage();
        home.clickOnLogin();
    }

    @AfterClass
    public void tearDown() throws IOException{
        WebActionWrappers wrap = new WebActionWrappers("Exiting driver");
        wrap.tearDown();
    }
}
