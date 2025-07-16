package example.ui.shopify.components;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Card extends Component {
    @FindBy(id = "card-image")
    public WebElement image;

    @FindBy(id = "card-title")
    public WebElement title;

    @FindBy(css = "#card-color canvas")
    public WebElement color;

    @FindBy(id = "card-price")
    public WebElement price;

    @FindBy(id = "card-size")
    public WebElement size;

    @FindBy(id = "card-gender")
    public WebElement gender;

    @FindBy(id = "add_to_cart_button")
    public WebElement addToCart;

    public Card(ComponentContext context) {
        super(context);
    }
}
