package IonErm.components.shopify;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartItem extends Component {

    @FindBy(css = ".cart-card-image")
    public WebElement cartImage;
    @FindBy(tagName = "h4")
    public WebElement title;
    @FindBy(css = ".cart-card-info > p:nth-child(2)")
    public WebElement price;
    @FindBy(css = ".cart-card-info > p:nth-child(3)")
    public WebElement color;
    @FindBy(css = ".cart-card-info > p:nth-child(4)")
    public WebElement size;
    @FindBy(css = ".remove-item")
    public WebElement removeButton;
    @FindBy(css = ".item-quantity")
    public WebElement quantity;
    @FindBy(css = ".minus-item")
    public WebElement minus;
    @FindBy(css = ".plus-item")
    public WebElement plus;

    public CartItem(WebElement parent) {
        super(parent);
    }
}
