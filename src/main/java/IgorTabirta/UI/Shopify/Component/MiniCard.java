package IgorTabirta.UI.Shopify.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniCard extends Component {

    @FindBy(tagName = "h4")
    public WebElement title;

    public MiniCard(WebElement parent) {
        super(parent);
    }
}
