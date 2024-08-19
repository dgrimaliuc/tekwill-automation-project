package serghei_rubailo.ui.shopify.pages.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenderFilterSection extends Component {

    public GenderFilterSection(WebElement parent) {
        super(parent);
    }

    @FindBy(css = "input[value='Male']")
    public WebElement genderMale;

    @FindBy(css = "input[value='Female']")
    public WebElement genderFemale;
}
