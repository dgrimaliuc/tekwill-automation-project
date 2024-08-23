package IgorTabirta.UI.PetStore.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdoptionsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t=single-adoption]")
    public List<WebElement> adoptions;

    public AdoptionsSection(WebElement parent) {
        super(parent);
    }
}
