package demo.selenium_tests.zcommerce.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import demo.selenium_tests.wrappers.WebActionWrappers;

public class Login {

    private WebActionWrappers wrap;

    private final String url = "https://zcommerce.crio.do/login";

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//a[@href='/signup']")
    WebElement signUpLink;

    public Login(WebActionWrappers wrap){
        this.wrap = wrap;
        PageFactory.initElements(new AjaxElementLocatorFactory(wrap.getDriver(), 10), this);
    }
    
    public void goToHomePage() throws IOException{
        wrap.get(url);
    }

    public Home tryLogin(String emailId, String password) throws InterruptedException, IOException{
        wrap.sendKeys(emailInput, emailId);
        wrap.sendKeys(passwordInput, password);
        wrap.click(submitButton);
   
        if(wrap.getDriver().getCurrentUrl().equals("https://zcommerce.crio.do/")){
            return new Home(wrap);
        }
        return null;     
    }

    public Register goToRegister() throws InterruptedException, IOException{
        wrap.click(signUpLink);
        return new Register(wrap);
    }
}
