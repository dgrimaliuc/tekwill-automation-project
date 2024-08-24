package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdoptionsSection extends Component {
    public AdoptionsSection(WebElement parent) {
        super(parent);
    }

    @FindBy(tagName = "h2")
    public WebElement header;
}
