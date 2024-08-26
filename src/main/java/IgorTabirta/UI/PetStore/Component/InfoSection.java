package IgorTabirta.UI.PetStore.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InfoSection extends Component {

    @FindBy(css = "[data-t=pets-count]")
    public WebElement petsCount;

    @FindBy(css = "[data-t=adoptions-count]")
    public WebElement adoptionCount;

    public InfoSection(WebElement parent) {
        super(parent);
    }
}
