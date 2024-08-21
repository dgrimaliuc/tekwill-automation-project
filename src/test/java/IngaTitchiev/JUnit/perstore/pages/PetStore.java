package IngaTitchiev.JUnit.perstore.pages;

import IngaTitchiev.JUnit.perstore.components.AdoptionSection;
import IngaTitchiev.JUnit.perstore.components.Location;
import IngaTitchiev.JUnit.perstore.components.PetsSection;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PetStore extends BasePage {

    @FindBy(css = "[data-t=location-section]")
    public Location location;

    @FindBy(css = "[data-t=pets-section]")
    public PetsSection petSection;

    @FindBy(css = "[data-t=adoptions-section]")
    public AdoptionSection adoptionSection;

    public PetStore(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://petstore-eb41f.web.app/");
    }
}
