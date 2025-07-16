package example.components.petStore;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniPet extends Component {
    @FindBy(xpath = ".//*[@data-t='error-reason']")
    public WebElement errorReason;

    public MiniPet(ComponentContext context) {
        super(context);
    }
}
