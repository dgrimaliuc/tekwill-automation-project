package Lilia_Rosca.components;

import example.ui.petstore.components.Adoption;
import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LR_adoption extends Component {

    @FindBy(xpath = ".//*[@data-t='pet-name']")
    // public List<WebElement> pets;
    public Components<LR_miniPet> pets;

    @FindBy(xpath = ".//*[@data-t='status-text']")
    public WebElement status;

    @FindBy(xpath = ".//*[@data-t='error-message']")
    public WebElement error;

    @FindBy(xpath = ".//button[@data-t='approve-button']")
    public WebElement approveButton;

    @FindBy(xpath = ".//button[@data-t='deny-button']")
    public WebElement denyButton;

    public LR_adoption(WebElement parent) {
        super(parent);

    }
}
