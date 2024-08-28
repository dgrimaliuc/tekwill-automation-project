package victor_murashev.ui.shopify.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartSection extends Component {

    @FindBy(css = ".total-price")
    public WebElement totalPrice;

    @FindBy(css = ".order-button")
    public WebElement orderButton;

    @FindBy(tagName = "H4")
    public Components<ThemesInCartSection> themeNamesInCart;

    public CartSection(WebElement parent) {
        super(parent);
    }
}
