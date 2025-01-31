package Lilia_Rosca.components.LR_shopify;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_PriceSection extends Component {
// 29.01
    @FindBy(css = "[value = 'Under $25']")
    public WebElement under25;

    @FindBy(css = "[value = '$25 to $50']")
    public WebElement p25To50;

    @FindBy(css = "[value = '$50 to $100']")
    public WebElement p50To100;

    @FindBy(css = "[value = 'Over $100']")
    public WebElement over100;

    public LR_PriceSection(WebElement parent) {
        super(parent);
    }
}
