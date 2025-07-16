package example.components.shopify;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PriceSection extends Component {

    @FindBy(css = "[value='Under $25']")
    public WebElement under25;
    @FindBy(css = "[value='$25 to $50']")
    public WebElement _25To50;
    @FindBy(css = "[value='$50 to $100']")
    public WebElement _50To100;
    @FindBy(css = "[value='Over $100']")
    public WebElement over100;

    public PriceSection(ComponentContext context) {
        super(context);
    }
}
