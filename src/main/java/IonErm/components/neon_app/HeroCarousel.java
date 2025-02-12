package IonErm.components.neon_app;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeroCarousel extends Component {

    //    @FindBy(css = "[class*=pagination-wrapper] button[class*=hero-carousel__page]")
    @FindBy(css = "[class*=hero-carousel-pagination-wrapper] [id*='hero-tab-']")
    public Components<Tab> tabs;

    @FindBy(css = "[class*=arrow-left]")
    public WebElement arrowLeft;

    @FindBy(css = "[class*=arrow-right]")
    public WebElement arrowRight;

    @FindBy(css = "[class*=placeholder]")
    public List<WebElement> placeholders;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCard> heroCards;

    @FindBy(css = "[class*=hero-card-container]:not([class*=inactive])")
    public HeroCard activeCard;

    public HeroCarousel(WebElement parent) {
        super(parent);
    }
}
