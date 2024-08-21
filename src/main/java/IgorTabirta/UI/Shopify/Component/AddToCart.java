package IgorTabirta.UI.Shopify.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToCart extends Component {

    @FindBy(id = "add_to_cart_button")
    public WebElement addToCart;


    public AddToCart(WebElement parent) {
        super(parent);
    }

}
