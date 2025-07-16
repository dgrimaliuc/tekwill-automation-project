package example.ui.petstore.pages;

import example.ui.petstore.components.AdoptionsSection;
import example.ui.petstore.components.InformationSection;
import example.ui.petstore.components.Location;
import example.ui.petstore.components.PetsSection;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class PetStore extends BasePage {

    @FindBy(css = "[data-t=location-section]")
    public Location location;

    @FindBy(css = "[data-t=pets-section]")
    public PetsSection petsSection;

    @FindBy(css = "[data-t=adoptions-section]")
    public AdoptionsSection adoptionsSection;

    @FindBy(css = "[data-t=info-section]")
    public InformationSection informationSection;

    public PetStore(WebDriver driver) {
        super(driver);
    }

    public void openPage(String location) {
        driver.get("https://petstore-eb41f.web.app/?location=" + location);
    }

    public void openPage() {
        driver.get("https://petstore-eb41f.web.app/");
    }

    public void addPets(int count) {
        petsSection.addPets(count);
    }

    public List<Map<String, String>> addAdoptions(List<String> pets, int count) {
        return adoptionsSection.addAdoptions(location.locationInput.getAttribute("value"), pets, count);
    }
}
