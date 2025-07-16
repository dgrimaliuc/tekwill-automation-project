package example.ui.petstore.components;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Pet extends Component {
    @FindBy(css = "[data-t=pet-name]")
    public WebElement name;

    @FindBy(css = "[data-t=pet-status]")
    public WebElement status;

    @FindBy(css = "[data-t=checkbox]")
    public WebElement checkbox;

    public Pet(ComponentContext context) {
        super(context);
    }

    public boolean isPetChecked() {
        System.out.println("Checking if pet is checked");
        return !this.checkbox.findElements(By.cssSelector("[data-t=checked-icon]")).isEmpty();
    }
}
