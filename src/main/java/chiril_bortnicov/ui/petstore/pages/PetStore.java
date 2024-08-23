package chiril_bortnicov.ui.petstore.pages;

import chiril_bortnicov.ui.petstore.components.AdoptionSection;
import chiril_bortnicov.ui.petstore.components.Location;
import chiril_bortnicov.ui.petstore.components.PetSection;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PetStore extends BasePage {

    @FindBy(css = "[data-t=location-section")
    public Location location;

    @FindBy(css = "[data-t=pets-section]")
    public PetSection petSection;

    @FindBy(css = "[data-t=adoptions-section]")
    public AdoptionSection adoptionSection;

    public void openPage() {
        driver.get("https://petstore-eb41f.web.app/");
    }

    public PetStore(WebDriver driver) {
        super(driver);
    }
}
