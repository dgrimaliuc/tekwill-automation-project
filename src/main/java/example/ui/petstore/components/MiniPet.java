package example.ui.petstore.components;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniPet extends Component {

    @FindBy(css = "[data-t=pet-name]")
    public WebElement name;

    @FindBy(css = "[data-t=error-reason]")
    public WebElement status;

    public MiniPet(ComponentContext context) {
        super(context);
    }
}
