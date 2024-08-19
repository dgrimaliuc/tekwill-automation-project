package serghei_rubailo.ui.shopify.pages.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SizeFilterSection extends Component {
    public SizeFilterSection(WebElement parent) {
        super(parent);
    }

    @FindBy(css = "input[value='S']")
    public WebElement sizeS;

    @FindBy(css = "input[value='M']")
    public WebElement sizeM;

    @FindBy(css = "input[value='L']")
    public WebElement sizeL;

    @FindBy(css = "input[value='XL']")
    public WebElement sizeXL;
}
