package denis_grimaliuc.ui.petstore.pages;

import denis_grimaliuc.ui.petstore.components.AdoptionsSection;
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

    public PetStore(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://petstore-eb41f.web.app/");
    }
}
