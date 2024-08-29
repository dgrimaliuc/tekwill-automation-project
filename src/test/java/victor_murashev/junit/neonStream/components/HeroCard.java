package victor_murashev.junit.neonStream.components;

import IgorTabirta.UI.Shopify.Component.ColorFilterSection;
import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeroCard extends Component {

    @FindBy(css = "[class*='hero-card_title']")
    public List<WebElement> cardTitle;


    @FindBy(css = "[class*='hero-card_description']")
    public List<WebElement> cardDescription;

    public HeroCard(WebElement parent) {
        super(parent);
    }
}
