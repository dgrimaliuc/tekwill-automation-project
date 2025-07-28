package Magda_Petrachi.PetStore;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InfoSection extends Component {

    @FindBy(css = "[data-t=info-section] .text-2xl")
    public WebElement title;

    @FindBy(css = "[data-t=pets-count]")
    public WebElement petCount;

    @FindBy(css = "[data-t=adoptions-count]")
    public WebElement adoptionCount;

    public InfoSection(WebElement parent) {
        super(parent);
    }


}
