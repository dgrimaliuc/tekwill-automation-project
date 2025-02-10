package denis_grimaliuc.components.petStore;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Pet extends Component {

    @FindBy(xpath = ".//div[@data-t='pet-name']")
    public WebElement name;

    @FindBy(xpath = ".//*[@data-t='pet-status']")
    public WebElement status;
    @FindBy(xpath = ".//*[@data-t='checked-icon']")
    public WebElement checkedIcon;

    public Pet(WebElement parent) {
        super(parent);
    }
}
