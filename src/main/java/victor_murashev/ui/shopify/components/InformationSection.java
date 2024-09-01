package victor_murashev.ui.shopify.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InformationSection extends Component {

    @FindBy(css = "[data-t='pets-count'] span")
    public WebElement petsCount;

    @FindBy(css = "[data-t='adoptions-count'] span")
    public WebElement adoptionCount;

    public InformationSection(WebElement parent) {
        super(parent);
    }
}
