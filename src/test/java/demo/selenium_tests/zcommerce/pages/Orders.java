package demo.selenium_tests.zcommerce.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import demo.selenium_tests.wrappers.WebActionWrappers;

public class Orders {

    private WebActionWrappers wrap;

    @FindBy(xpath = "//div[@class='chakra-accordion__item css-1xgejrs']")
    List<WebElement> orderCards;

    public Orders(WebActionWrappers wrap){
        this.wrap = wrap;
        PageFactory.initElements(new AjaxElementLocatorFactory(wrap.getDriver(), 10), this);
    }


}
