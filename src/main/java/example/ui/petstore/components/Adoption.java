package example.ui.petstore.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Adoption extends Component {

    @FindBy(css = "[data-t=status-text]")
    public WebElement status;

    @FindBy(css = "[data-t=pets-list]> div")
    public Components<MiniPet> pets;

    @FindBy(css = "[data-t=error-message]")
    public WebElement error;

    @FindBy(css = "[data-t=approve-button]")
    public WebElement approveButton;

    @FindBy(css = "[data-t=deny-button]")
    public WebElement denyButton;

    public Adoption(ComponentContext context) {
        super(context);
    }
}
