package chiril_bortnicov.ui.petstore.pages;


import chiril_bortnicov.ui.petstore.components.AdoptionsSection;
import chiril_bortnicov.ui.petstore.components.InformationSection;
import chiril_bortnicov.ui.petstore.components.Location;
import chiril_bortnicov.ui.petstore.components.PetsSection;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

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
}

