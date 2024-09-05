package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PetsSection extends Component {
    public PetsSection(WebElement parent) {
        super(parent);
    }

    @FindBy(tagName = "h2")
    public WebElement header;

    @FindBy(css = "[data-t=single-pet]")
    public Components<Pet> pets;

    @FindBy(css = "[data-t=add-pet-button]")
    public WebElement addPetButton;

    @FindBy(css = "[data-t=adopt-button]")
    public WebElement adoptButton;

    @FindBy(css = "[data-t=pet-name-input]")
    public WebElement petNameInput;

    @FindBy(css = "[data-t=deselect-button]")
    public WebElement deselectButton;

    public void addPets(int quantity) {
        for (int i = 0; i < quantity; i++) {
            addPetButton.click();
        }
    }

    public void selectFirst(int quantity) {
        for (int i = 0; i < quantity; i++) {
            pets.get(i).petCheckbox.click();
        }
    }

}
