package victor_murashev.ui.shopify.pages;

import denis_grimaliuc.ui.shopify.components.Card;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import victor_murashev.ui.shopify.components.CartSection;
import victor_murashev.ui.shopify.components.ThemesSection;

public class ShopifyPOM extends BasePage {


    @FindBy(css = "#card-container")
    public ThemesSection themeSection;

    // Prices
    @FindBy(css = "#card-price")
    public WebElement cardPrices;

    @FindBy(css = ".card")
    public Components<Card> cards;

    @FindBy(css = ".cart-button")
    public WebElement cartIcon;

    @FindBy(css = ".cart-cards-wrapper")
    public CartSection cartSection;

    public ShopifyPOM(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }
}
