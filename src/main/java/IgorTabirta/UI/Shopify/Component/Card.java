package IgorTabirta.UI.Shopify.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Card extends Component {

    @FindBy(id = "card-image")
    public WebElement image;

    @FindBy(css = "card-color canvas")
    public WebElement cardColor;

    @FindBy(id = "card-title")
    public WebElement cardTitle;

    @FindBy(id = "card-price")
    public WebElement cardPrice;

    @FindBy(id = "card-size")
    public WebElement cardSize;

    @FindBy(id = "card-gender")
    public WebElement cardGender;

    @FindBy(id = "add_to_cart_button")
    public WebElement addToCart;

    @FindBy(id = "card-title")
    public WebElement title;

    public Card(WebElement parent) {
        super(parent);
    }

}
