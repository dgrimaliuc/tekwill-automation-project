package chiril_bortnicov.ui.shopify.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenderFilterSection extends Component {

    @FindBy(css = "input[value=Male]")
    public WebElement male;
    @FindBy(css = "input[value=Female]")
    public WebElement female;


    public GenderFilterSection(WebElement parent) {
        super(parent);
    }
}
