package denis_grimaliuc.neonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCard extends Component {

    @FindBy(css = "a[class*=styles_title]")
    public WebElement title;

    @FindBy(css = "div[class*=styles_description]")
    public WebElement description;
    @FindBy(css = "[class*=styles_hero-card-picture]")
    public WebElement picture;
    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement watchNow;
    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement addToWatchlist;
    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement removeFromWatchlist;

    public HeroCard(WebElement parent) {
        super(parent);
    }
}
