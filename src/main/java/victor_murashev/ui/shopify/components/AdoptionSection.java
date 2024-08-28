package victor_murashev.ui.shopify.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdoptionSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t='single-adoption']")
    public List<WebElement> adoptionsList;

    public AdoptionSection(WebElement parent) {
        super(parent);
    }
}
