package IngaTitchiev.JUnit.perstore.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PetsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t=add-pet-button]")
    public WebElement addPetButton;

    @FindBy(css = "[data-t=adopt-button]")
    public WebElement adoptionButton;

    @FindBy(css = "[data-t=single-pet]")
    public Components<Pet> pets;

    @FindBy(css = "[data-t=deselect-button]")
    public WebElement deselectButton;

    @FindBy(css = "[data-t=pet-name-input]")
    public WebElement petNameInput;

    public PetsSection(WebElement parent) {
        super(parent);
    }

    public void addPets(int count) {
        for (int i = 0; i < count; i++) {
            addPetButton.click();
        }
    }

    public void selectFirst(int count) {
        for (int i = 0; i < count; i++) {
            pets.get(i).checkbox.click();
        }
    }


}
