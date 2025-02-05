package Lilia_Rosca.poms;

import Lilia_Rosca.components.LR_shopify.*;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_shopifyPage extends BasePage {
// 29.01
    @FindBy(css = "#dropdownButton")
    public WebElement dropdownButton;

    @FindBy(css = "#dropdownList li:nth-child(1)")
    public WebElement ascendingOrder;

    @FindBy(css = "#dropdownList li:nth-child(2)")
    public WebElement descendingOrder;

    @FindBy(css = "[data-t = price-section]")
    public LR_PriceSection priceSection;

    @FindBy(css = "[data-t = color-section]")
    public LR_ColorSection colorSection; // modified 31.01

    @FindBy(css = "[data-t = size-section]")
    public LR_SizeSection sizeSection; // modified 31.01

    @FindBy(css = "[data-t = gender-section]")
    public LR_GenderSection genderSection; // modified 31.01

    @FindBy(css = ".card")
    public Components<LR_Card> cards;

// 31.01
    @FindBy(css = ".cart-button")
    public WebElement cartButton;
// 03.02
    @FindBy(css = ".cart-wrapper")
    public LR_Cart cart;

    public LR_shopifyPage(WebDriver driver) {
        super(driver);
    }
}