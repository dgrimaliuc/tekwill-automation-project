package lilia_toma.Shopify;

import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyPageLT extends BasePage {
    @FindBy(css = "[data-t='price-section']")
    public CardPrice cardPrice;


    @FindBy(xpath = "//input[@value='Under $25']")
    public WebElement under25PriceFilter;

    @FindBy(xpath = "//input[@value='$25 to $50']")
    public WebElement price25to50Filter;

    @FindBy(xpath = "//input[@value='$50 to $100']")
    public WebElement price50to100Filter;

    @FindBy(xpath = "//input[@value='Over $100']")
    public WebElement over100PriceFilter;

    @FindBy(css = "[value*='Under $25']")
    public WebElement priceUnder25C;

    @FindBy(css = "[value*='$25 to $50']")
    public WebElement price25To50C;

    @FindBy(css = "[value*='$50 to $100']")
    public WebElement price50To100C;

    @FindBy(css = "[value*='Over $100']")
    public WebElement priceOver100C;

    @FindBy(xpath = "//*[@value='Black']")
    public WebElement blackColorFilter;

    @FindBy(xpath = "//*[@value='White']")
    public WebElement whiteColorFilter;

    @FindBy(xpath = "//*[@value='Green']")
    public WebElement greenColorFilter;

    @FindBy(xpath = "//*[@value='Purple']")
    public WebElement purpleColorFilter;

    @FindBy(xpath = "//input[@value='S']")
    public WebElement sSizeFilter;

    @FindBy(xpath = "//input[@value='M']")
    public WebElement mSizeFilter;

    @FindBy(xpath = "//input[@value='L']")
    public WebElement lSizeFilter;

    @FindBy(xpath = "//input[@value='Male']")
    public WebElement maleGenderFilter;

    @FindBy(xpath = "//input[@value='Female']")
    public WebElement femaleGenderFilter;

    @FindBy(css = ".cart-button")
    public WebElement cartButton;


//    @FindBy(xpath = "//div[@class='card']")
//    public List<WebElement> cards;
/** In cazul Components, inlocuim FindBy de mai sus cu urmatorul:*/
    @FindBy(css = ".card")
    public Components<Card> cards;

    @FindBy(css = ".cart-wrapper")
    public Cart cart;

    @FindBy(css = "#card-image")
    public List<WebElement> images;

    @FindBy(css = "#add_to_cart_button")
    public List<WebElement> addToCartButton;

    @FindBy(css = "#card-title")
    public List<WebElement> titles;

    @FindBy(xpath = "//p[@id='card-price']")
    public List<WebElement> prices;

    @FindBy(xpath = "//*[@id='card-color']/canvas")
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


    public ShopifyPageLT(WebDriver driver) {
        super(driver);
    }

    public static Integer formatPrice(String priceText) {
        return Integer.parseInt(priceText.replaceAll("\\$", "").trim());
    }

}

