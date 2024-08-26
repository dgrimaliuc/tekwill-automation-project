package IgorTabirta.UI.PetStore.Component;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Pet extends Component {

    @FindBy(css = "[data-t=pet-name]")
    public WebElement name;

    @FindBy(css = "[data-t=pet-status]")
    public WebElement status;

    @FindBy(css = "[data-t=checkbox]")
    public WebElement checkBox;


    public Pet(WebElement parent) {
        super(parent);
    }

    public Boolean isPetChecked() {
        return !checkBox.findElements(By.cssSelector("[data-t=checked-icon]")).isEmpty();
    }
}
