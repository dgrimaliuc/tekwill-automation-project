package example.components.neonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeroCarousel extends Component {

    @FindBy(css = "[class*=pagination-wrapper] button[class*=hero-carousel__page]")
    public Components<Tab> tabs;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCard> cards;

    @FindBy(css = "[class*=hero-card-container]:not([class*=inactive])")
    public HeroCard activeCard;

    @FindBy(css = "[class*=arrow-left]")
    public WebElement leftArrow;
    @FindBy(css = "[class*=arrow-right]")
    public WebElement rightArrow;

    @FindBy(css = "[class*=placeholder]")
    public List<WebElement> placeholders;

    public HeroCarousel(ComponentContext context) {
        super(context);
    }
}
