package denis_grimaliuc.shopify;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyPage extends BasePage {
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


    @FindBy(xpath = "//div[@class='card']")
    public List<WebElement> cards;

    @FindBy(xpath = "//p[@id='card-price']")
    public List<WebElement> prices;

    @FindBy(xpath = "//*[@id='card-color']/canvas")
    public List<WebElement> colors;


    public ShopifyPage(WebDriver driver) {
        super(driver);
    }


    public static Integer formatPrice(String priceText) {
        return Integer.parseInt(priceText.replaceAll("\\$", "").trim());
    }


}
