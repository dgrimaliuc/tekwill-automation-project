package IngaTitchiev.JUnit.perstore.components;

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
    public WebElement checkbox;

    public Pet(WebElement parent) {
        super(parent);
    }

    public boolean isPetChecked() {
        System.out.println("Checking if pet is checked");
        return !checkbox.findElements(By.cssSelector("[data-t=checked-icon]")).isEmpty();
    }
}
