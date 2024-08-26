package IngaTitchiev.JUnit.perstore.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    public PetsSection(WebElement parent) {
        super(parent);
    }
}
