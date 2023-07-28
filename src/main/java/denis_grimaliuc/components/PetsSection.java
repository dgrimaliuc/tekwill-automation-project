package denis_grimaliuc.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetsSection extends Component {

    @FindBy(xpath = "//button[text()=' Adopt selected pets']")
    public WebElement adoptButton;

    @FindBy(xpath = ".//h2")
    public WebElement sectionTitle;

    @FindBy(xpath = ".//input[@type='text']")
    public WebElement petNameInput;

    @FindBy(xpath = "//button[text()=' Add Rescue']")
    public WebElement addPetBtn;

    @FindBy(xpath = ".//button[text()='Deselect']")
    public WebElement deselect;

    @FindBy(xpath = "//table/tbody/tr[.//div[not(contains(text(),'No rows. Try reset filters'))]]")
    public Components<Pet> pets;

}
