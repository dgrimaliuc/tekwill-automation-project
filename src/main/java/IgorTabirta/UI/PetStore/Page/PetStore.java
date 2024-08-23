package IgorTabirta.UI.PetStore.Page;

import IgorTabirta.UI.PetStore.Component.AdoptionsSection;
import IgorTabirta.UI.PetStore.Component.InfoSection;
import IgorTabirta.UI.PetStore.Component.LocationSection;
import IgorTabirta.UI.PetStore.Component.PetsSection;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PetStore extends BasePage {

    public void openPage() {
        driver.get("https://petstore-eb41f.web.app/");
    }

    @FindBy(css = "[data-t=location-section]")
    public LocationSection location;


    @FindBy(css = "[data-t=pets-section]")
    public PetsSection petsSection;

    @FindBy(css = "[data-t=adoptions-section]")
    public AdoptionsSection adoptionsSection;

    @FindBy(css = "[data-t=info-section]")
    public InfoSection infoSection;

    public PetStore(WebDriver driver) {
        super(driver);
    }
}
