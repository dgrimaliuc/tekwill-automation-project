package chiril_bortnicov.ui.petstore.components;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PetsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t=add-pet-button]")
    public WebElement addPetBtn;

    @FindBy(css = "[data-t=adopt-button]")
    public WebElement adoptBtn;

    @FindBy(css = "[data-t=single-pet]")
    public List<WebElement> pets;

    public static final By petsLocator = By.cssSelector("[data-t=single-pet]");

    public void addPets(int count) {
        for (int i = 0; i < count; i++) {
            addPetBtn.click();

        }
    }

    public PetsSection(WebElement parent) {
        super(parent);
    }
}
