package victor_murashev.ui.shopify.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SauceProductsInCartSection extends Component {

    @FindBy(css = ".inventory_item_name")
    public WebElement productName;

    public SauceProductsInCartSection(WebElement parent) {
        super(parent);
    }
}
