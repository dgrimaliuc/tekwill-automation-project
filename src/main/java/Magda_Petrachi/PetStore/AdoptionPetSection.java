package Magda_Petrachi.PetStore;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdoptionPetSection extends Component {

    @FindBy(css = "[data-t=adoptions-section] .text-2xl")
    public WebElement title;

    @FindBy(css = "[data-t=status-text]")
    public WebElement status;


    public AdoptionPetSection(WebElement parent) {
        super(parent);
    }
}
