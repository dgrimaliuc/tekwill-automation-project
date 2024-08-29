package victor_murashev.junit.neonStream.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeroCarousel extends Component {

    @FindBy(css="[alt='Scrollleft']")
    public WebElement arrowLeft;

    @FindBy(css="[alt='Scrollright']")
    public WebElement arrowRight;

    @FindBy(css="[id*='hero-tab]'")
    public List<WebElement> tabs;

    public HeroCarousel(WebElement parent) {
        super(parent);
    }
}
