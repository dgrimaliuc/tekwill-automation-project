package Magda_Petrachi.PetStore;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetInSection extends Component {

    @FindBy(css = "[data-t=pets-section] .text-2xl")
    public WebElement title;

    @FindBy(css = "[data-t=pet-name-input]")
    public WebElement petNameInput;

    @FindBy(css = "[data-t=add-pet-button]")
    public WebElement addPetButton;

    @FindBy(css = "[data-t=adopt-button]")
    public WebElement adoptionButton;


    @FindBy(xpath = "//div[text()='No rows. Try reset filters']")
    public WebElement noResult;


    @FindBy(css = "[data-t=pets-section] .text-2xl]")
    public WebElement availablePetInLocation;


    public PetInSection(WebElement parent) {
        super(parent);
    }


}
