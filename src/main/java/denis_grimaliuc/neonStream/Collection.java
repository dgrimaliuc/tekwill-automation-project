package denis_grimaliuc.neonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Collection extends Component {

    @FindBy(css = ".carousel-title")
    public WebElement title;

    @FindBy(css = "[class*=arrow_left]")
    public WebElement arrowLeft;

    @FindBy(css = "[class*=arrow_right]")
    public WebElement arrowRight;

    @FindBy(css = ".scrolling-section > div:has([class*=browse-card])")
    public Components<BrowseCard> cards;

    public Collection(WebElement parent) {
        super(parent);
    }
}
