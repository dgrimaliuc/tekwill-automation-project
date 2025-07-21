package Magda_Petrachi.Shopify;

import example.components.shopify.CartItem;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartShopify extends Components {
    @FindBy(css = ".cart-card")
    public Components<CartItem> cartItems;

    @FindBy(css = ".total-price")
    public WebElement totalPrice;

    @FindBy(css = ".order-button")
    public WebElement orderButton;

    public CartShopify(List components) {
        super(components);
    }

}
