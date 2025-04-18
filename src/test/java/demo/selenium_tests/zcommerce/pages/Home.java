package demo.selenium_tests.zcommerce.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import demo.selenium_tests.wrappers.WebActionWrappers;

public class Home {

    private WebActionWrappers wrap;

    private final String url = "https://www.zcommerce.crio.do";

    @FindBy(xpath = "//img[@alt='ZCommerce-icon']")
    WebElement zcLogo;

    @FindBy(xpath = "//button[@class='chakra-button css-1ai4qkv']")
    WebElement logInButton;

    @FindBy(xpath = "//input[@placeholder='Search an Item']")
    WebElement searchBar;

    @FindBy(xpath = "//div[@class='chakra-input__right-addon cursor-pointer css-rj9jnu']")
    WebElement searchButton;

    @FindBy(xpath = "//a[@href='/cart']")
    WebElement cartButton;

    @FindBy(xpath = "//div[@class='flex items-center justify-center gap-2']")
    WebElement profileDropdown;

    @FindBy(xpath = "//a[@href='/order-history']")
    WebElement orderHistoryButton;

    @FindBy(xpath = "//h3[@class='text-red-400 text-lg font-semibold cursor-pointer']")
    WebElement logOutButton;

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

    private final By addToCard = new By.ByXPath("//button[text()='ADD TO CART']");
    
    @FindBy(xpath = "//div[@class='bg-[#042C22] flex md:flex-row gap-10 flex-col py-8 px-4 md:px-14 w-full md:justify-between md:items-end']")
    WebElement footerCard;

    public Home(WebActionWrappers wrap){
        this.wrap = wrap;
        PageFactory.initElements(new AjaxElementLocatorFactory(wrap.getDriver(), 10), this);
    }

    public void goToHomePage() throws IOException{
        wrap.get(url);
    }

    public void logOut() throws InterruptedException, IOException{
        // return to normal state
        wrap.click(zcLogo);

        // click on dropdown and select logout
        wrap.click(profileDropdown);
        wrap.click(logOutButton);
    }

    public Login clickOnLogin() throws InterruptedException, IOException{
        wrap.click(logInButton);
        return new Login(wrap);
    }

    public Orders goToOrderHistory() throws InterruptedException, IOException{
        // return to normal state
        wrap.click(zcLogo);

        // click on dropdow and select order history
        wrap.click(profileDropdown);
        wrap.click(orderHistoryButton);

        return new Orders(wrap);

    }

    public void searchForProduct(String word) throws InterruptedException, IOException{
        wrap.sendKeys(searchBar, word);
    }

    public void addProductToCard(Integer index) throws InterruptedException, IOException{
        wrap.click(regularItemCards.get(index).findElement(addToCard));
    }

    public void addProductToCard(String name) throws InterruptedException, IOException{
        for(WebElement we: regularItemCards){
            if(we.getText().toLowerCase().contains(name)) wrap.click(we.findElement(addToCard));
        }
    }

    public Checkout proceedToCheckout() throws InterruptedException, IOException{
        wrap.click(cartButton);
        return new Checkout(wrap);
    }
}
