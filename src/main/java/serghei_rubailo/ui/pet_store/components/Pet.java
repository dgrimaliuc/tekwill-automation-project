package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Pet extends Component {
    public Pet(WebElement parent) {
        super(parent);
    }

    @FindBy(css = "[data-t=pet-name]")
    public WebElement petName;

    @FindBy(css = "[data-t=pet-status]")
    public WebElement petStatus;

    @FindBy(css = "[data-t=checkbox]")
    public WebElement petCheckbox;

    public boolean isPetChecked() {
        return !petCheckbox.findElements(By.cssSelector("[data-t=checked-icon]")).isEmpty();
    }

}
