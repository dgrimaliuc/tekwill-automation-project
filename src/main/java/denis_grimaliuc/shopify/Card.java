package denis_grimaliuc.shopify;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Card extends Component {

    @FindBy(css = "#card-title")
    public WebElement title;

    @FindBy(css = "#card-color canvas")
    public WebElement color;

    @FindBy(css = "#card-price")
    public WebElement price;

    @FindBy(css = "#card-size")
    public WebElement size;

    @FindBy(css = "#card-image")
    public WebElement image;

    @FindBy(css = "#card-gender")
    public WebElement gender;
    @FindBy(css = "#add_to_cart_button")
    public WebElement addToCartButton;

    public Card(ComponentContext context) {
        super(context);
    }
}
