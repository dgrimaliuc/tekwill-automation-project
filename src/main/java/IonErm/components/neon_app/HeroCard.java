package IonErm.components.neon_app;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCard extends Component {

    @FindBy(css = "[class*=styles_title]")
    public WebElement title;

    @FindBy(css = "img[class*=hero-card-picture]")
    public WebElement image;

    @FindBy(css = "[class*=styles_description]")
    public WebElement description;

    @FindBy(css = "[class*=actions_watch-now]")
    public WebElement watchNow;

    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement addToWatchlist;

    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement removeFromWatchlist;

    public HeroCard(WebElement parent) {
        super(parent);
    }
}
