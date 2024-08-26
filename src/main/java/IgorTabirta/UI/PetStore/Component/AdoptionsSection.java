package IgorTabirta.UI.PetStore.Component;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdoptionsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t=single-adoption]")
    public Components<Adoption> adoptions;

    public AdoptionsSection(WebElement parent) {
        super(parent);
    }
}
