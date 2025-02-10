package IonErm.components.pet_store;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Adoptions extends Component {

    @FindBy(xpath = ".//*[@data-t='status-text']")
    public WebElement status;

    @FindBy(xpath = ".//*[@data-t='pet-name']")
    public Components<MiniPet> pets;

    @FindBy(xpath = ".//*[@data-t='approve-button']")
    public WebElement clickApproveBtn;

    @FindBy(xpath = ".//*[@data-t='deny-button']")
    public WebElement clickDenyBtn;

    @FindBy(xpath = ".//*[@data-t='error-message']")
    public WebElement errorMessage;

    public Adoptions(WebElement parent) {
        super(parent);
    }
}
