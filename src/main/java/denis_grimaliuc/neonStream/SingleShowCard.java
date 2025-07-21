package denis_grimaliuc.neonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SingleShowCard extends Component {

    @FindBy(css = "a:has(.single-card-poster)")
    public WebElement image;

    @FindBy(css = ".single-card-title")
    public WebElement title;

    @FindBy(css = ".single-card-description")
    public WebElement description;
    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement watchNowButton;
    @FindBy(css = "[class*=placeholder]")
    public List<WebElement> placeholders;

    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement addToWatchlist;
    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement removeFromWatchlist;

    public SingleShowCard(WebElement parent) {
        super(parent);
    }
}
