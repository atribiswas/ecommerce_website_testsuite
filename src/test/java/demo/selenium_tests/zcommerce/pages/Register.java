package demo.selenium_tests.zcommerce.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import demo.selenium_tests.wrappers.WebActionWrappers;

public class Register {

    private WebActionWrappers wrap;

    private final String url = "hhttps://zcommerce.crio.do/signup";

    @FindBy(xpath = "//input[@name='name']")
    WebElement nameInput;

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    public Register(WebActionWrappers wrap){
        this.wrap = wrap;
        PageFactory.initElements(new AjaxElementLocatorFactory(wrap.getDriver(), 10), this);
    }
    
    public void goToHomePage() throws IOException{
        wrap.get(url);
    }  

    public Home tryRegister(String name, String emailId, String password) throws InterruptedException, IOException{
        wrap.sendKeys(nameInput, name);
        wrap.sendKeys(emailInput, emailId);
        wrap.sendKeys(passwordInput, password);
        wrap.click(submitButton);

        Thread.sleep(2000);
        if(!wrap.getDriver().getCurrentUrl().contains("signup")){
            return new Home(wrap);
        }
        return null;
    }
}
