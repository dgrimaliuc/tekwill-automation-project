package serghei_rubailo.ui.pet_store.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Adoption extends Component {
    public Adoption(WebElement parent) {
        super(parent);
    }

    @FindBy(css = "[data-t=status-text]")
    public WebElement status;

    @FindBy(css = "[data-t=pet-name]")
    public List<WebElement> pets;

}
