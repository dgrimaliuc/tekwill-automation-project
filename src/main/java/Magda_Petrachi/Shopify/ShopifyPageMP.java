package Magda_Petrachi.Shopify;

import example.components.shopify.Cart;
import helpers.BasePage;
import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyPageMP extends BasePage {

    public static WebElement under25Filter;
    public static Object gender;


    @FindBy(xpath = "//input[@value='Under $25']") //"//input[@value='Under $25']"
    public WebElement selectFilterUnder;

    @FindBy(xpath = "//input[@value='$25 to $50']")
    ////input[@value='$25 to $50']
    public WebElement price25to50Filter;

    @FindBy(xpath = "//input[@value='$50 to $100']") //"//input[@value='$50 to $100']"
    public WebElement price50to100Filter;

    @FindBy(xpath = "//input[@value='Over $100']") //"//input[@value='Over $100']"
    public WebElement over100PriceFilter;

    @FindBy(xpath = "//*[@value='Black']") //"//*[@value='Black']"
    public WebElement blackColorFilter;

    @FindBy(xpath = "//*[@value='White']") //"//*[@value='White']"
    public WebElement whiteColorFilter;

    @FindBy(xpath = "//input[@value='S']") //"//*[@value='White']"
    public WebElement sSizeFilter;


    @FindBy(css = "#add_to_card_button")
    public WebElement addToCardButton;


    @FindBy(css = "#card_titles")
    public List<WebElement> titles;

//    @FindBy(xpath = "//div[@class='card']") //"//div[@class='card']"
//    public List<WebElement> cards;

    @FindBy(xpath = "//p[@id='card-price']") //"//p[@id='card-price']"
    public List<WebElement> prices;


    @FindBy(css = ".cards")
    public Components<CardShopify> cards;

    @FindBy(css = ".cart-cards-wrapper")
    public Cart cart;

    @FindBy(xpath = "//*[@id='card-color']/canvas") //"//*[@id='card-color']/canvas"
    public List<WebElement> colors;

    @FindBy(xpath = "//p[@id='card-size")
    public List<WebElement> sizes;

    @FindBy(xpath = "#card-gender")
    public List<WebElement> genders;
    public Component CardButton;
    public WebElement cartButton;


    public ShopifyPageMP(WebDriver driver) {
        super(driver);
    }

    public static Integer formatPrice(String priceText) {
        return Integer.parseInt(priceText.replaceAll("\\$", "").trim());
    }

    private class components<T> {
        public void getFirst() {
        }
    }
}
