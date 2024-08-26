package victor_murashev.ui.shopify.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventorySection extends Component {

    @FindBy(css = ".inventory_item_img")
    public WebElement image;

    @FindBy(css = ".inventory_item_name")
    public WebElement title;

    @FindBy(css = ".inventory_item_price")
    public WebElement price;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement addToCart;

    public InventorySection(WebElement parent) {
        super(parent);
    }
}

