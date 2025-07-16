package example.ui.shopify.components;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniCard extends Component {

    @FindBy(tagName = "h4")
    public WebElement title;

    public MiniCard(ComponentContext context) {
        super(context);
    }
}
