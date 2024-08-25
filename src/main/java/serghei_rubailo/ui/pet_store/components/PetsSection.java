package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
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
    public List<WebElement> pets;

//    public WebElement petsLocator

    @FindBy(css = "[data-t=add-pet-button]")
    public WebElement addPetButton;

    @FindBy(css = "[data-t=adopt-button]")
    public WebElement adoptButton;

    public void addPets(int quantity) {
        for (int i = 0; i < 5; i++) {
            addPetButton.click();
        }
    }

}
