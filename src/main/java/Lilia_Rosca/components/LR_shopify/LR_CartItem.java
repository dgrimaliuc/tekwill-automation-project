package Lilia_Rosca.components.LR_shopify;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_CartItem extends Component {
// 31.01
    @FindBy(css = ".cart-card-image")
    public WebElement image;

    @FindBy(tagName = "h4")
    public WebElement title;

    @FindBy(css = ".cart-card-info > p:nth-child(2)")
    public WebElement price;

    @FindBy(css = ".cart-card-info > p:nth-child(3)")
    public WebElement color;

    @FindBy(css = ".cart-card-info > p:nth-child(4)")
    public WebElement size;

    @FindBy(css = ".item-quantity")
    public WebElement quantity;

    @FindBy(css = ".minus-item")
    public WebElement minusButton;

    @FindBy(css = ".plus-item")
    public WebElement plusButton;

    @FindBy(css = ".remove-item")
    public WebElement removeButton;

    public LR_CartItem(WebElement parent) {
        super(parent);
    }
}
