package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniPet extends Component {
    public MiniPet(WebElement parent) {
        super(parent);
    }

    @FindBy(css = "[data-t=pet-name]")
    public WebElement petName;

    @FindBy(css = "[data-t=error-reason]")
    public WebElement errorReason;

}
