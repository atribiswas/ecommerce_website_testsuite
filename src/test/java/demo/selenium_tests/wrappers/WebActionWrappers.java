package demo.selenium_tests.wrappers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestNGMethod;

import demo.selenium_tests.BaseTestSelenium;

public class WebActionWrappers extends BaseTestSelenium {

    public WebActionWrappers(String test) throws IOException{
        super();
        createReporter(test);
    }
    
    public void get(String url) throws IOException {
        try {
            driver.get(url);
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                            .equals("complete"));
            
            info("Page loaded successfully");
        } catch (TimeoutException e) {
            error("Error while waiting for page to load");
        }
    }

    public void click(By locator) throws InterruptedException, IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

            element.click();

            Thread.sleep(500);
            info("Element has been clicked");
        } catch (ElementClickInterceptedException e) {
            error("Element Click Intercepted");
        } catch (NoSuchElementException e) {
            error("Error finding element while clicking");
        }
    }

    public void sendKeys(By locator, String text) throws InterruptedException, IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            element.clear();
            element.sendKeys(text);

            Thread.sleep(500);
            info("Text has been sent");
        } catch (NoSuchElementException e) {
            error("Error finding element while sending text");
        }
    }

    public String getText(By locator) throws InterruptedException, IOException {
        String text = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            text = element.getText();

            Thread.sleep(500);
            info("Text has been sent");
        } catch (NoSuchElementException e) {
            error("Error finding element while sending text");
        }
        return text;
    }

    public WebDriver getDriver(){
        return driver;
    }

}
