package example.components.neonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Carousel extends Component {

    @FindBy(css = ".carousel-title")
    public WebElement title;
    @FindBy(css = "[class*=controls_arrow_left]")
    public WebElement leftArrow;
    @FindBy(css = "[class*=controls_arrow_right]")
    public WebElement rightArrow;

    @FindBy(css = ".scrolling-section > div")
    public Components<BrowseCard> cards;

    public Carousel(ComponentContext context) {
        super(context);
    }
}
