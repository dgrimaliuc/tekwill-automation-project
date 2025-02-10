package CMComponents.neonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Carousel extends Component{

@FindBy(css = ".carousel-title")
public WebElement title;

@FindBy(css = "[class*=controls_arrow_left]")
public WebElement leftArrow;

@FindBy(css = "[class*=controls_arrow_right]")
public WebElement rightArrow;

@FindBy (css = ".scrolling-section > div")
public Components<BrowseCard> cards;


    public Carousel(WebElement parent) {
        super(parent);
    }
}
