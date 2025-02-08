package Lilia_Rosca.components.LR_NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_HeroCard extends Component {
// 05.02
    @FindBy(css = "a[class*=title]")
    public WebElement title;

    @FindBy(css = "img[class*=hero-card-picture]")
    public WebElement image;

    @FindBy(css = "[class*=description]")
    public WebElement description;

    @FindBy(css = "button[class*=watch-now-btn]")
    public WebElement watchNow;

    @FindBy(css = " [data-t=not-in-watchlist]")
    public WebElement addToWatchList;

    @FindBy(css = " [data-t=in-watchlist]")
    public WebElement removeFromWatchList;

    public LR_HeroCard(WebElement parent) {
        super(parent);
    }
}
