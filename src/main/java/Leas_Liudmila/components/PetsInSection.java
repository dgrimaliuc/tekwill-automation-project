package Leas_Liudmila.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PetsInSection extends Component {

    @FindBy(xpath = "//table/tbody/tr[.//div[not(contains(text(),'No rows. Try reset filters'))]]")
    public List<WebElement> pets;

    @FindBy(xpath = ".//td/span")
    public List<WebElement> petsStatus;

    @FindBy(xpath = ".//tr/td/div/div/div")
    public List<WebElement> petsName;

    @FindBy(xpath = "//button[text()=' Adopt selected pets']")
    public WebElement adoptSelPetsBtnActive;


    public PetsInSection(WebElement parent) {
        super(parent);
    }
}
