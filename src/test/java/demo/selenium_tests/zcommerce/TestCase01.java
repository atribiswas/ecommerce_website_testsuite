package demo.selenium_tests.zcommerce;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import demo.selenium_tests.wrappers.WebActionWrappers;

public class TestCase01 {

    private WebActionWrappers wrap;

    @Test
    public void goToWebsite() throws IOException, InterruptedException{
        wrap = new WebActionWrappers("Going To Website");

        

        // wrap.get("https://www.zcommerce.crio.do");
        // wrap.click(By.xpath("//button[text()='Login']"));
        // wrap.sendKeys(By.xpath("//input[@name='email' and @type='email']"), "Content-team123@criodo.com");
        // wrap.sendKeys(By.xpath("//input[@name='password' and @type='password']"), "Content-team123@criodo.com");
        // wrap.click(By.xpath("//button[@type='submit' and text()='Login']"));
    }

    @AfterClass
    public void tearDown() throws IOException{
        WebActionWrappers wrap = new WebActionWrappers("Exiting driver");
        wrap.tearDown();
    }
}
