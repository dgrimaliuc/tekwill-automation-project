package IonErm.poms.shopify;

import IonErm.components.shopify.*;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IonErm_ShopifyPage extends BasePage {

    @FindBy(css = ".cart-button")
    public WebElement cartButton;

    @FindBy(css = ".cart-wrapper")
    public Cart cart;

    @FindBy(css = "#dropdownButton")
    public WebElement dropdownButton;

    @FindBy(css = "#dropdownList li:nth-child(1)")
    public WebElement ascendingOrder;

    @FindBy(css = "#dropdownList li:nth-child(2)")
    public WebElement descendingOrder;

    @FindBy(css = "[data-t='price-section']")
    public PriceSection priceSection;

    @FindBy(css = "[data-t='color-section']")
    public ColorSection colorSection;

    @FindBy(css = "[data-t='size-section']")
    public SizeSection sizeSection;

    @FindBy(css = "[data-t='gender-section']")
    public GenderSection genderSection;

    @FindBy(css = ".card")
    public Components<Card> cards;

    public IonErm_ShopifyPage(WebDriver driver) {
        super(driver);
    }
}
