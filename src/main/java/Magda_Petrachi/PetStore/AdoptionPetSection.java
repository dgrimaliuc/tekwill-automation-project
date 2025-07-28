package Magda_Petrachi.PetStore;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdoptionPetSection extends Component {

    @FindBy(xpath = "//div[@data-t='adoptions-section']//h2[contains(@class, 'text-2xl') and text()='Adoptions in']")
    public WebElement title;

    @FindBy(css = "[data-t=status-text]")
    public WebElement status;


    public AdoptionPetSection(WebElement parent) {
        super(parent);
    }

    
}
