package lilia_toma.Shopify;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.print.DocFlavor;
import java.util.List;

import static java.lang.Integer.parseInt;
import static javax.management.openmbean.SimpleType.STRING;

public class ShopifyPageLT extends BasePage {
    @FindBy(xpath = "//input[@value='Under $25']")
    public WebElement under25PriceFilter;

    @FindBy(xpath = "//input[@value='$25 to $50']")
    public WebElement price25to50Filter;

    @FindBy(xpath = "//input[@value='$50 to $100']")
    public WebElement price50to100Filter;

    @FindBy(xpath = "//input[@value='Over $100']")
    public WebElement over100PriceFilter;

    @FindBy(xpath = "//*[@value='Black']")
    public WebElement blackColorFilter;

    @FindBy(xpath = "//*[@value='White']")
    public WebElement whiteColorFilter;

    @FindBy(xpath = "//*[@value='Green']")
    public WebElement greenColorFilter;

    @FindBy(xpath = "//*[@value='Purple']")
    public WebElement purpleColorFilter;


    @FindBy(xpath = "//div[@class='card']")
    public List<WebElement> cards;

    @FindBy(xpath = "//p[@id='card-price']")
    public List<WebElement> prices;

    @FindBy(xpath = "//*[@id='card-color']/canvas")
    public List<WebElement> colors;



    public ShopifyPageLT(WebDriver driver) {
        super(driver);
    }

    public static Integer formatPrice(String priceText) {
        return Integer.parseInt(priceText.replaceAll("\\$", "").trim());
    }

}

