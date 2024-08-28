package victor_murashev.ui.shopify.components;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PetsSection extends Component {

    public static final By petsLocator = By.cssSelector("[data-t='single-pet']");

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t='add-pet-button']")
    public WebElement addPetButton;

    @FindBy(css = "[data-t='adopt-button']")
    public WebElement adoptPetButton;

    @FindBy(css = "[data-t='single-pet']")
    public List<WebElement> petsList;

    public void addPets(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            addPetButton.click();
            Thread.sleep(100);
        }
    }

    public PetsSection(WebElement parent) {
        super(parent);
    }
}
