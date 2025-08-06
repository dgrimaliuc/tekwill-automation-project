package Magda_Petrachi.PetStore;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetCard extends Component {

    @FindBy(css = "[data-t=pet-status]")
    public WebElement petStatus;

    @FindBy(css = "[data-t=pet-name]")
    public WebElement petName;

    @FindBy(css = "[data-t=checkbox]")
    public WebElement petSelection;


    public PetCard(WebElement parent, WebElement petStatus) {
        super(parent);

    }


    public void getFirst() {
        return;
    }
}
