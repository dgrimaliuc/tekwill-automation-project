package denis_grimaliuc.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PetsInSection extends Component {

    @FindBy(xpath = "//button[text()=' Adopt selected pets']")
    public WebElement adoptButton;

    @FindBy(xpath = "//div[@id='root']/div/div[3]//input[@type='text']")
    public WebElement petNameInput;

    @FindBy(xpath = "//button[text()=' Add Rescue']")
    public WebElement addPetBtn;

    @FindBy(xpath = "//table/tbody/tr[.//div[not(contains(text(),'No rows. Try reset filters'))]]")
    public List<WebElement> pets;

}
