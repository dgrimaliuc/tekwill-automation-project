package Magda_Petrachi.NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SimplePromoCard extends Component {

    @FindBy(css = ".single-card-img-wrapper a:not([class])")
    public WebElement imageLeft;

    @FindBy(css = "a.image-right")
    public WebElement imageRight;

    @FindBy(css = ".single-show-card-title")
    public WebElement title;

    @FindBy(css = ".single-show-card-description")
    public WebElement description;

    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement watchNowButton;

    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement addToWatchlist;

    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement removeFromWatchlist;


    public SimplePromoCard(WebElement parent) {
        super(parent);
    }
}