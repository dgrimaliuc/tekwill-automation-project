package denis_grimaliuc.components;

//import example.ui.petstore.components.MiniPet;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Adoption extends Component {
    //
    @FindBy(xpath = ".//*[@data-t='pet-name']")
    public Components<MiniPet> pets;


//    @FindBy(xpath = ".//*[@data-t='pet-name']")
//    public List<WebElement> pets;

    @FindBy(xpath = ".//*[@data-t='status-text']")
    public WebElement status;

    @FindBy(xpath = ".//*[@data-t='error-message']")
    public WebElement error;

    @FindBy(xpath = ".//button[@data-t='approve-button']")
    public WebElement approveBtn;
    @FindBy(xpath = ".//button[@data-t='deny-button']")
    public WebElement denyBtn;


    public Adoption(WebElement parent) {
        super(parent);
    }
}
