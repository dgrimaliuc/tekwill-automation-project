package Lilia_Rosca.poms;

import Lilia_Rosca.components.LR_shopify.LR_Card;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LR_shopifyPage extends BasePage {

 /*   dropdownButton
    ascendingOrder
    descendingOrder
    priceSection
*/

    @FindBy(css = ".card")
    public Components<LR_Card> card;

    /*
    priceSection
    colorSection
    sizeSection
    genderSection
    */

    public LR_shopifyPage(WebDriver driver) {
        super(driver);
    }
}
