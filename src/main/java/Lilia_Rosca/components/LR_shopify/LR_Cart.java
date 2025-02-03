package Lilia_Rosca.components.LR_shopify;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_Cart extends Component {
    // 31.01
        @FindBy(css = ".cart-card")
        public Components<LR_CartItem> cartItems;

        @FindBy(css = ".total-price")
        public WebElement totalPrice;

        @FindBy(css = ".order-button")
        public WebElement orderButton;

        public LR_Cart(WebElement parent) {
            super(parent);
        }
    }
