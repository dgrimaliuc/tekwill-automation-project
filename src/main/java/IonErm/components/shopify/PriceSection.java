package IonErm.components.shopify;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PriceSection extends Component {

    @FindBy(css = "[value='Under $25']")
    public WebElement under25;

    @FindBy(css = "[value='$25 to $50']")
    public WebElement price25to50;

    @FindBy(css = "[value='$50 to $100']")
    public WebElement price50to100;

    @FindBy(css = "[value='Over $100']")
    public WebElement priceOver100;

    public PriceSection(WebElement parent) {
        super(parent);
    }
}