package denis_grimaliuc.shopify;

import helpers.customElements.Component;
import helpers.customElements.Components;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart extends Component {
    @FindBy(css = ".cart-card")
    public Components<CartItem> cartItems;
    @FindBy(css = ".total-price")
    public WebElement totalPrice;

    public WebElement totalPrice() {
        return totalPrice;
    }

    @FindBy(css = ".order-button")
    public WebElement orderButton;
    @FindBy(css = ".empty-cart-container")
    public WebElement emptyContainer;
    @FindBy(css = ".empty-cart-title")
    public WebElement emptyTitle;

    public Cart(ComponentContext context) {
        super(context);
    }
}
