package example.ui.shopify.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartSection extends Component {

    @FindBy(css = ".total-price")
    public WebElement totalPrice;

    @FindBy(css = ".order-button")
    public WebElement orderButton;

    @FindBy(css = ".cart-card")
    public Components<MiniCard> miniCards;

    public CartSection(ComponentContext context) {
        super(context);
    }
}
