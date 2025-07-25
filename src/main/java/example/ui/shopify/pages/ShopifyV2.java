package example.ui.shopify.pages;

import example.ui.shopify.components.Card;
import example.ui.shopify.components.CartSection;
import example.ui.shopify.components.GenderFilterSection;
import example.ui.shopify.components.PriceFilterSection;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopifyV2 extends BasePage {

    @FindBy(css = "[data-t='price-section']")
    public PriceFilterSection priceFilters;

    @FindBy(css = "[data-t=gender-section]")
    public GenderFilterSection genderFilters;

    @FindBy(css = ".card")
    public Components<Card> cards;

    @FindBy(xpath = "//*[@class='cart-button']")
    public WebElement cartIcon;

    @FindBy(css = ".cart-cards-wrapper")
    public CartSection cartSection;

    public ShopifyV2(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }
}
