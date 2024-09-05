package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdoptionsSection extends Component {
    public AdoptionsSection(WebElement parent) {
        super(parent);
    }

    @FindBy(tagName = "h2")
    public WebElement header;

    @FindBy(css = "[data-t=single-adoption]")
    public Components<Adoption> adoptions;
}
