package IgorTabirta.UI.Neon.Component;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeroCarusel extends Component {

    @FindBy(css = "[class*=arrow-left]")
    public WebElement leftArrow;

    @FindBy(css = "[class*=arrow-right]")
    public WebElement rightArrow;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCard> cards;

    @FindBy(css = "[id*=hero-tab]")
    public List<WebElement> tabs;

    public HeroCarusel(WebElement parent) {
        super(parent);
    }
}
