package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Location extends Component {
    public Location(WebElement parent) {
        super(parent);
    }

    @FindBy(id = "location-input")
    public WebElement locationInput;

    @FindBy(css = "[data-t=change-location]")
    public WebElement changeLocationButton;

    @FindBy(css = "[data-t=open-in-new-tab]")
    public WebElement openInNewTabButton;
}
