package chiril_bortnicov.ui.petstore.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    public PetSection(WebElement parent) {
        super(parent);
    }
}
