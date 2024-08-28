package IngaTitchiev.JUnit.perstore.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdoptionSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t=single-adoptions]")
    public Components<Adoption> adoptions;

    public AdoptionSection(WebElement parent) {
        super(parent);
    }
}
