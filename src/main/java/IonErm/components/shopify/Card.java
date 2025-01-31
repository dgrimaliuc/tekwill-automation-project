package IonErm.components.shopify;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Card extends Component {
    @FindBy(css = "#card-image")
    public WebElement cardImage;

    @FindBy(css = "#card-title")
    public WebElement cardTitle;

    @FindBy(css = "#card-description")
    public WebElement cardDescription;

    @FindBy(css = "#card-color canvas")
    public WebElement cardColor;

    @FindBy(css = "#card-price")
    public WebElement cardPrice;

    @FindBy(css = "#card-size")
    public WebElement cardSize;

    @FindBy(css = "#card-gender")
    public WebElement cardGender;

    @FindBy(css = "#add_to_cart_button")
    public WebElement clickAddToCartBtn;

    public Card(WebElement parent) {
        super(parent);
    }
}