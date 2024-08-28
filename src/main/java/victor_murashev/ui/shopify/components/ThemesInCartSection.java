package victor_murashev.ui.shopify.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThemesInCartSection extends Component {
    @FindBy(tagName = "H4")
    public WebElement themeName;

    public ThemesInCartSection(WebElement parent) {
        super(parent);
    }
}
