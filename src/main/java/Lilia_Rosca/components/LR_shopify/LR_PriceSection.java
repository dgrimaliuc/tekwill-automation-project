package Lilia_Rosca.components.LR_shopify;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_PriceSection extends Component {

/*    @FindBy(value = "")
    public WebElement under25;*/
    /* under 25
     25 - 50
     50 - 100
     other 100
    */
    public LR_PriceSection(WebElement parent) {
        super(parent);
    }
}
