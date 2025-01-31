package Lilia_Rosca.poms;

import Lilia_Rosca.components.LR_shopify.LR_Card;
import Lilia_Rosca.components.LR_shopify.LR_PriceSection;
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
    public LR_PriceSection colorSection;

    @FindBy(css = "[data-t = size-section]")
    public LR_PriceSection sizeSection;

    @FindBy(css = "[data-t = gender-section]")
    public LR_PriceSection genderSection;

    @FindBy(css = ".card")
    public Components<LR_Card> cards;


    public LR_shopifyPage(WebDriver driver) {
        super(driver);
    }
}
