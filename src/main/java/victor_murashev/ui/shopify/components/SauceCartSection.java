package victor_murashev.ui.shopify.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SauceCartSection extends Component {

    @FindBy(css = ".total-price")
    public WebElement totalPrice;

    @FindBy(css = "#checkout")
    public WebElement checkoutButton;

    @FindBy(css = ".inventory_item_name")
    public Components<SauceProductsInCartSection> productNamesInCart;

    public SauceCartSection(WebElement parent) {
        super(parent);
    }
}
