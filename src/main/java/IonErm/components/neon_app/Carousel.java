package IonErm.components.neon_app;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Carousel extends Component {

    @FindBy(css = "h2[class*=carousel-title]")
    public WebElement title;

    @FindBy(css = "[class*=controls_arrow_left]")
    public WebElement arrowLeft;

    @FindBy(css = "[class*=controls_arrow_right]")
    public WebElement arrowRight;

    @FindBy(css = "[class*=scrolling-section]> div")
    public Components<BrowseCard> cards;
    
    public Carousel(WebElement parent) {
        super(parent);
    }
}
