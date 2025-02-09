package IonErm.components.neon_app;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SinglePromoCard extends Component {

    @FindBy(css = ".single-promo-card .image-right")
    public WebElement image;

    @FindBy(css = ".single-show-card-title")
    public WebElement title;

    @FindBy(css = ".single-show-card-description")
    public WebElement description;

    @FindBy(css = "button[class*=actions_watch-now-btn]")
    public WebElement watchNow;

    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement addToWatchlist;

    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement removeFromWatchlist;


    public SinglePromoCard(WebElement parent) {
        super(parent);
    }
}
