package denis_grimaliuc.shopify;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyPage extends BasePage {
    @FindBy(css = "input[value='Under $25']")
    public WebElement under25PriceFilter;
    @FindBy(css = "input[value='$25 to $50']")
    public WebElement price25to50Filter;
    @FindBy(xpath = "//input[@value='$50 to $100']")
    public WebElement price50to100Filter;
    @FindBy(xpath = "//input[@value='Over $100']")
    public WebElement over100PriceFilter;

    @FindBy(xpath = "//*[@value='Black']")
    public WebElement blackColorFilter;

    @FindBy(xpath = "//*[@value='White']")
    public WebElement whiteColorFilter;
    @FindBy(xpath = "//*[@value='Red']")
    public WebElement redColorFilter;

    @FindBy(xpath = "//input[@value='S']")
    public WebElement sSizeFilter;
    @FindBy(xpath = "//input[@value='M']")
    public WebElement mSizeFilter;

    @FindBy(xpath = "//input[@value='XL']")
    public WebElement xlSizeFilter;

    @FindBy(xpath = "//input[@value='Male']")
    public WebElement maleGenderFilter;

    @FindBy(xpath = "//input[@value='Female']")
    public WebElement femaleGenderFilter;

    @FindBy(css = ".cart-button")
    public WebElement cartButton;


    @FindBy(xpath = "//div[@class='card']")
    public List<WebElement> cards;

    @FindBy(css = "#card-image")
    public List<WebElement> images;

    @FindBy(css = "#add_to_cart_button")
    public List<WebElement> addToCartButtons;

    @FindBy(css = "#card-title")
    public List<WebElement> titles;

    @FindBy(css = "#card-price")
    public List<WebElement> prices;

    @FindBy(css = "#card-color>canvas")
    public List<WebElement> colors;

    @FindBy(xpath = "//p[@id='card-size']")
    public List<WebElement> sizes;

    @FindBy(xpath = "//p[@id='card-gender']")
    public List<WebElement> genders;


    @FindBy(css = ".cart-card-image")
    public WebElement cartItemImage;

    @FindBy(css = ".cart-card-info h4")
    public WebElement cartItemTitle;

    @FindBy(css = ".cart-card-info p:nth-child(2)")
    public WebElement cartItemPrice;

    @FindBy(css = ".cart-card-info p:nth-child(3)")
    public WebElement cartItemColor;

    @FindBy(css = ".cart-card-info p:nth-child(4)")
    public WebElement cartItemSize;
    @FindBy(css = ".item-quantity")
    public WebElement cartItemQuantity;

    @FindBy(css = ".total-price")
    public WebElement cartTotalPrice;


    public ShopifyPage(WebDriver driver) {
        super(driver);
    }


    public static Integer formatPrice(String priceText) {
        return Integer.parseInt(priceText.replaceAll("\\$", "").trim());
    }


}
