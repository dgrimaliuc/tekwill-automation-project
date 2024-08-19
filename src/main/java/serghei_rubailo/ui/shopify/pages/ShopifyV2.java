package serghei_rubailo.ui.shopify.pages;

import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import serghei_rubailo.ui.shopify.pages.components.Card;
import serghei_rubailo.ui.shopify.pages.components.GenderFilterSection;
import serghei_rubailo.ui.shopify.pages.components.PriceFilterSection;
import serghei_rubailo.ui.shopify.pages.components.SizeFilterSection;

import java.util.List;

public class ShopifyV2 extends BasePage {

    public ShopifyV2(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-t='price-section']")
    public PriceFilterSection pricesFilterSection;

    @FindBy(css = "[data-t='size-section']")
    public SizeFilterSection sizeFilterSection;

    @FindBy(css = "[data-t='gender-section']")
    public GenderFilterSection genderFilterSection;

    @FindBy(css = ".card")
    public Components<Card> cards;


    public void open() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

}
