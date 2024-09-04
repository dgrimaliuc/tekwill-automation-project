package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetsInfoSection extends Component {
    public PetsInfoSection(WebElement parent) {
        super(parent);
    }

    @FindBy(tagName = "h2")
    public WebElement header;

    @FindBy(css = "[data-t=pets-count] span")
    public WebElement petsCountText;

    @FindBy(css = "[data-t=adoptions-count] span")
    public WebElement adoptionsCountText;

}
