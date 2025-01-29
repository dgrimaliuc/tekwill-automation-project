package Lilia_Rosca.components.LR_pets;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_miniPet extends Component {

    @FindBy(xpath = ".//*[@data-t='error-reason']")
    public WebElement errorReason;

    public LR_miniPet(WebElement parent) {
        super(parent);
    }
}
