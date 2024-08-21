package IngaTitchiev.JUnit.perstore.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdoptionSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    public AdoptionSection(WebElement parent) {
        super(parent);
    }
}
