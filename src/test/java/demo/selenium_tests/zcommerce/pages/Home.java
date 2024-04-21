package demo.selenium_tests.zcommerce.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import demo.selenium_tests.wrappers.WebActionWrappers;

public class Home {

    private WebActionWrappers wrap;

    @FindBy(xpath = "//img[@alt='ZCommerce-icon']")
    WebElement zcLogo;

    @FindBy(xpath = "//input[@placeholder='Search an Item']")
    WebElement searchBar;

    @FindBy(xpath = "//div[@class='chakra-input__right-addon cursor-pointer css-rj9jnu']")
    WebElement searchButton;

    @FindBy(xpath = "//a[@href='/cart']")
    WebElement cartButton;

    @FindBy(xpath = "//div[@class='flex items-center justify-center gap-2']")
    WebElement profileDropdown;

    @FindBy(xpath = "//div[@class='swiper-slide swiper-slide-active']")
    WebElement banner;

    @FindBy(xpath = "//div[@class='swiper swiper-initialized swiper-horizontal h-full w-full']")
    WebElement reccomendedGroup;

    @FindBy(xpath = "//div[@class='swiper-slide max-w-[320px]']")
    List<WebElement> recommendedItemCards;

    @FindBy(xpath = "//div[@class='grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-4 xl:gap-x-8 gap-6']")
    WebElement allProductsGrid;

    @FindBy(xpath = "//div[@class='flex flex-col gap-2 min-w-[200px] max-w-[350px] cursor-pointer']")
    List<WebElement> regularItemCards;
    
    @FindBy(xpath = "//div[@class='bg-[#042C22] flex md:flex-row gap-10 flex-col py-8 px-4 md:px-14 w-full md:justify-between md:items-end']")
    WebElement footerCard;

    public Home(WebActionWrappers wrap){
        this.wrap = wrap;
        PageFactory.initElements(new AjaxElementLocatorFactory(wrap.getDriver(), 0), getClass());
    }

    

}
