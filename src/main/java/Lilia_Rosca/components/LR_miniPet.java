package Lilia_Rosca.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LR_miniPet extends Component {

    @FindBy(xpath = ".//*[@data-t='error-reason']")
    public WebElement errorReason;

    public LR_miniPet(WebElement parent) {
        super(parent);
    }
}
