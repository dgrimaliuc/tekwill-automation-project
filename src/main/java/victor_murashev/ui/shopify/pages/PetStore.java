package victor_murashev.ui.shopify.pages;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import victor_murashev.ui.shopify.components.Location;
import victor_murashev.ui.shopify.components.PetsSection;

public class PetStore extends BasePage {

    @FindBy(css = "[data-t='location-section']")
    public Location location;

    public void openPage() {
        driver.get("https://petstore-eb41f.web.app");
    }

    @FindBy(css = "[data-t='pets-section']")
    public PetsSection petsSection;

    @FindBy(css = "[data-t='adoptions-section']")
    public PetsSection adoptionSection;

    public PetStore(WebDriver driver) {
        super(driver);
    }
}
