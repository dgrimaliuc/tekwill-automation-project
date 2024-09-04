package serghei_rubailo.ui.pet_store.pages;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import serghei_rubailo.ui.pet_store.components.AdoptionsSection;
import serghei_rubailo.ui.pet_store.components.Location;
import serghei_rubailo.ui.pet_store.components.PetsInfoSection;
import serghei_rubailo.ui.pet_store.components.PetsSection;

public class PetStore extends BasePage {
    public PetStore(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://petstore-eb41f.web.app/");
    }

    public void openPage(String location) {
        driver.get("https://petstore-eb41f.web.app/?location=" + location);
    }

    @FindBy(css = "[data-t=location-section]")
    public Location location;

    @FindBy(css = "[data-t=pets-section]")
    public PetsSection petsSection;

    @FindBy(css = "[data-t=adoptions-section]")
    public AdoptionsSection adoptionsSection;

    @FindBy(css = "[data-t=info-section]")
    public PetsInfoSection petsInfoSection;
}
