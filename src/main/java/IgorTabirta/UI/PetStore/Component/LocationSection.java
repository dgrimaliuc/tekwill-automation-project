package IgorTabirta.UI.PetStore.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LocationSection extends Component {

    @FindBy(id = "location-input")
    public WebElement locationInput;

    @FindBy(css = "[data-t=change-location]")
    public WebElement changeLocationButton;

    @FindBy(css = "[data-t=open-in-new-tab]")
    public WebElement openInNewTabBtn;

    public LocationSection(WebElement parent) {
        super(parent);
    }

}
