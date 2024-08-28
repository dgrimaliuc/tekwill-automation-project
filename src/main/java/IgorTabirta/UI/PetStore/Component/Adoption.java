package IgorTabirta.UI.PetStore.Component;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Adoption extends Component {
    @FindBy(css = "[data-t=status-text]")
    public WebElement status;

    @FindBy(css = "[data-t=pet-name]")
    public List<WebElement> name;

    @FindBy(css = "[data-t=approve-button]")
    public WebElement approveBtn;

    @FindBy(css = "[data-t=deny-button]")
    public WebElement denyBtn;

    @FindBy(css = "[data-t=error-message]")
    public WebElement error;

    @FindBy(css = "[data-t=pets-list]> div")
    public Components<MiniPet> pets;


    public Adoption(WebElement parent) {
        super(parent);
    }
}
