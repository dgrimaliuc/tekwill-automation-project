package IgorTabirta.UI.PetStore.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdoptionsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    public AdoptionsSection(WebElement parent) {
        super(parent);
    }
}
