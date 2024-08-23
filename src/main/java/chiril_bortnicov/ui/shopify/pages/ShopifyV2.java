package chiril_bortnicov.ui.shopify.pages;

import chiril_bortnicov.ui.shopify.components.*;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ShopifyV2 extends BasePage {

    @FindBy(css = "[data-t='price-section']")
    public PriceFilterSection priceFilters;

    @FindBy(css = "[data-t='size-section']")
    public SizeFilterSection sizeFilters;

    @FindBy(css = "[data-t=gender-section]")
    public GenderFilterSection genderFilters;

    @FindBy(css = ".card")
    public Components<Card> cards;

    @FindBy(css = ".cart-cards-wrapper")
    public CartSection cartSection;

    @FindBy(css = ".cart-button")
    public WebElement cartIcon;


    public ShopifyV2(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }
}
