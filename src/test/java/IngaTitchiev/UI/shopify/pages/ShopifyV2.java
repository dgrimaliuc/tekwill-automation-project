package IngaTitchiev.UI.shopify.pages;

import IngaTitchiev.UI.shopify.components.*;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyV2 extends BasePage {
    @FindBy(css = "[data-t='price-section']")
    public WebElement pricesFilterSection;

    @FindBy(css = "[value='Under $25']")
    public WebElement under25;

    @FindBy(css = "[value='$25 to $50']")
    public WebElement beetween25and50;

    @FindBy(css = "[value='$50 to $100']")
    public WebElement beetween50and100;

    @FindBy(css = "[value='Over $100']")
    public WebElement morethan100;

    @FindBy(css = ".card")
    public List<WebElement> cards;

    @FindBy(css = "[data-t='price-section']")
    public PriceFilterSection priceSection;

    @FindBy(css = "[data-t='size-section']")
    public SizeFilterSection sizeSection;

    @FindBy(css = "[data-t='gender-section']")
    public GenderFilterSection genderSection;

    @FindBy(css = ".card")
    public Components<Card> cardsC;

    @FindBy(css = ".cart-button")
    public WebElement cartItem;

    @FindBy(css = ".cart-cards-wrapper")
    public CartSection cartSection;

    public ShopifyV2(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }
}
