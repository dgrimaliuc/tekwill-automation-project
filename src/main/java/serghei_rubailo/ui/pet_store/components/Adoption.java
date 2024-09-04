package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Adoption extends Component {
    public Adoption(WebElement parent) {
        super(parent);
    }

    @FindBy(css = "[data-t=status-text]")
    public WebElement status;

    @FindBy(css = "[data-t=pets-list]")
    public Components<MiniPet> pets;

    @FindBy(css = "[data-t=approve-button]")
    public WebElement approveButton;

    @FindBy(css = "[data-t=deny-button]")
    public WebElement denyButton;

    @FindBy(css = "[data-t=error-message]")
    public WebElement errorMessage;


}
