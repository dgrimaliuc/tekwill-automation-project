package example.ui.petstore.components;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InformationSection extends Component {

    @FindBy(css = "[data-t=pets-count] span")
    public WebElement petsCount;
    @FindBy(css = "[data-t=adoptions-count] span")
    public WebElement adoptionsCount;

    public InformationSection(ComponentContext context) {
        super(context);
    }
}
