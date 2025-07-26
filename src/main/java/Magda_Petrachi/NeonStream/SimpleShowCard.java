package Magda_Petrachi.NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SimpleShowCard extends Component {

    @FindBy(css = ".single-card-poster")
    public WebElement image;

    @FindBy(css = ".single-card-title")
    public WebElement title;

    @FindBy(css = ".single-card-description")
    public WebElement description;

    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement watchNowButton;

    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement addToWatchlist;

    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement removeFromWatchlist;

    @FindBy(css = "[class*=placeholder]")
    public List<WebElement> placeholders;

    public SimpleShowCard(WebElement parent) {
        super(parent);
    }
}
