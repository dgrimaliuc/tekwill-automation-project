package chiril_bortnicov.ui.shopify.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniCard extends Component {

    @FindBy(tagName = "h4")
    public WebElement title;

    @FindBy(css = ".cart-card p:nth-of-type(1)")
    public WebElement price;

    @FindBy(css = ".cart-card p:nth-of-type(2)")
    public WebElement color;

    @FindBy(css = ".cart-card p:nth-of-type(3)")
    public WebElement size;

    public MiniCard(WebElement parent) {
        super(parent);
    }
}
