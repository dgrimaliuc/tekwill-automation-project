package IonErm.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniPet extends Component {

    @FindBy(xpath = "//*[@data-t='error-reason']")
    public WebElement errorReason;

    public MiniPet(WebElement parent) {
        super(parent);
    }
}
