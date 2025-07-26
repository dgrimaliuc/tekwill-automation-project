package lilia_toma.NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeroCard extends Component {
    @FindBy (css = "a[class*=styles_title]")
    public WebElement title;

    @FindBy (css = "div[class*=styles_description]")
    public WebElement description;

    @FindBy (css = "img[class*=styles_hero-card-picture]")
    public WebElement picture;

    @FindBy (css = "[class*=actions_watch-now-btn]")
    public WebElement watchNow;

//    @FindBy (css = "[class*=actions_watchlist-button]")
//    public WebElement watchList;
    @FindBy (css = "[data-t=not-in-watchlist]")
    public WebElement addToWatchList;

    @FindBy (css = "[data-t=in-watchlist]")
    public WebElement removeFromWatchList;






    public HeroCard(WebElement parent) {
        super(parent);
    }
}
