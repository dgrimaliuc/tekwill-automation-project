package denis_grimaliuc.neonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeroCarousel extends Component {
    @FindBy(css = "[class*=arrow-left]")
    public WebElement arrowLeft;
    @FindBy(css = "[class*=arrow-right]")
    public WebElement arrowRight;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCard> cards;

    @FindBy(css = "button[class*=hero-carousel__page]")
    public List<WebElement> tabs;

    public HeroCarousel(ComponentContext context) {
        super(context);
    }
}
