package example.ui.petstore.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PetsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;
    @FindBy(css = "[data-t=deselect-button]")
    public WebElement deselectBtn;
    @FindBy(css = "[data-t=pet-name-input]")
    public WebElement petNameInput;
    @FindBy(css = "[data-t=add-pet-button]")
    public WebElement addPetBtn;

    @FindBy(css = "[data-t=adopt-button]")
    public WebElement adoptBtn;
    @FindBy(xpath = "//*[contains(text(),'No rows')]")
    public WebElement defaultText;

    @FindBy(css = "[data-t=single-pet]")
    public Components<Pet> pets;

    public PetsSection(WebElement parent) {
        super(parent);
    }

    public List<Map<String, String>> addPets(String location, int count) {
        List<Map<String, String>> pets = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            var pet = addPet(location, "Cat");
//            pets.add(pet.jsonPath().getMap(""));
//        }
        return pets;
    }

    public void selectFirst(int count) {
        for (int i = 0; i < count; i++) {
            pets.get(i).checkbox.click();
        }
    }
}
