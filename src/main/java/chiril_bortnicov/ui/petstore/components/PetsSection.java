package chiril_bortnicov.ui.petstore.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PetsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t=add-pet-button]")
    public WebElement addPetBtn;

    @FindBy(css = "[data-t=adopt-button]")
    public WebElement adoptBtn;

    @FindBy(css = "#pets-table div.items-center")
    public WebElement defaultText;

    @FindBy(css = "[data-t=pet-name-input]")
    public WebElement petNameInput;

    @FindBy(css = "[data-t=deselect-button]")
    public WebElement deselectBtn;

    @FindBy(css = "[data-t=checkbox]")
    public WebElement checkbox;

    @FindBy(css = "[data-t=single-pet]")
    public Components<Pet> pets;


    public static final By petsLocator = By.cssSelector("[data-t=single-pet]");

    public void addPets(int count) {
        for (int i = 0; i < count; i++) {
            addPetBtn.click();

        }
    }

    public void selectFirst(int count) {
        for (int i = 0; i < count; i++) {
            pets.get(i).checkbox.click();

        }
    }

    public PetsSection(WebElement parent) {
        super(parent);
    }
}