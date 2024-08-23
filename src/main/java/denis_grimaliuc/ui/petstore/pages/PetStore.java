package denis_grimaliuc.ui.petstore.pages;

import denis_grimaliuc.ui.petstore.components.AdoptionsSection;
import denis_grimaliuc.ui.petstore.components.InformationSection;
import denis_grimaliuc.ui.petstore.components.Location;
import denis_grimaliuc.ui.petstore.components.PetsSection;
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
