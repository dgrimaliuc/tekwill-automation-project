package denis_grimaliuc.ui.shopify.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartSection extends Component {


    @FindBy(css = ".total-price")
    public WebElement totalPrice;

    @FindBy(css = ".order-button")
    public WebElement orderButton;

    @FindBy(css = ".cart-card")
    public Components<MiniCard> miniCards;

    public CartSection(WebElement parent) {
        super(parent);
    }
}
