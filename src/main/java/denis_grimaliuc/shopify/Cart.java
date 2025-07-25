package denis_grimaliuc.shopify;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart extends Component {

    @FindBy(css = ".cart-card")
    public Components<CartItem> cartItems;

    @FindBy(css = ".total-price")
    public WebElement totalPrice;
    @FindBy(css = ".order-button")
    public WebElement orderButton;
    @FindBy(css = ".empty-cart-container")
    public WebElement emptyContainer;
    @FindBy(css = ".empty-cart-title")
    public WebElement emptyTitle;

    public Cart(WebElement parent) {
        super(parent);
    }
}
