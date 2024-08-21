package IgorTabirta.UI.Shopify.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SizeFilterSection extends Component {

    @FindBy(css = "input[value='S']")
    public WebElement sizeS;
    @FindBy(css = "input[value='M']")
    public WebElement sizeM;
    @FindBy(css = "input[value='L']")
    public WebElement sizeL;
    @FindBy(css = "input[value='XL']")
    public WebElement sizeXL;

    public SizeFilterSection(WebElement parent) {
        super(parent);
    }

}
