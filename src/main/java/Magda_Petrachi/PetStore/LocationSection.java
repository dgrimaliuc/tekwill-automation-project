package Magda_Petrachi.PetStore;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LocationSection extends Component {

    @FindBy(css = "data-t=change-location")
    public WebElement changeLocation;

    @FindBy(css = "[data-t=open-in-new-tab]")
    public WebElement openNewTab;

    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement locationInput;

    public LocationSection(WebElement parent) {
        super(parent);
    }
}
