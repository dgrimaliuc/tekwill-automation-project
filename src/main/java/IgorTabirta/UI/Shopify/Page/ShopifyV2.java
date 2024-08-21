package IgorTabirta.UI.Shopify.Page;

import IgorTabirta.UI.Shopify.Component.AddToCart;
import IgorTabirta.UI.Shopify.Component.Card;
import IgorTabirta.UI.Shopify.Component.GenderFilterSection;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyV2 extends BasePage {

    @FindBy(css = "[data-t= 'price-section']")
    public IgorTabirta.UI.Shopify.Component.PriceFilterSection PriceFilterSection;


    @FindBy(css = "[data-t='size-section']")
    public IgorTabirta.UI.Shopify.Component.SizeFilterSection SizeFilterSection;

    @FindBy(css = "[data-t='color-section']")
    public IgorTabirta.UI.Shopify.Component.ColorFilterSection ColorFilterSection;


    @FindBy(css = ".card")
    public List<WebElement> card;

    @FindBy(css = "[data-t='gender-section']")
    public GenderFilterSection genderFilters;

    @FindBy(css = ".card")
    public Components<Card> cards;

    @FindBy(css = ".card")
    public AddToCart addToCart;

    @FindBy(css = ".cart-button")
    public WebElement cartIcon;

    public ShopifyV2(WebDriver driver) {
        super(driver);
    }


    public void open() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

}
