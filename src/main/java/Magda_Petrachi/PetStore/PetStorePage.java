package Magda_Petrachi.PetStore;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PetStorePage extends BasePage {

    @FindBy(css = "div[data-t=location-section]")
    public LocationSection locationSection;

    @FindBy(css = "[data-t=info-section]")
    public InfoSection infoSection;

    @FindBy(css = "[data-t=adoptions-section]")
    public AdoptionPetSection adoptionPetSection;

    @FindBy(css = "[data-t=pets-section]")
    public PetInSection petInSection;


    public PetStorePage(WebDriver driver) {
        super(driver);
    }
}
