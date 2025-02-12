package IonErm.poms.neon_app;

import IonErm.components.neon_app.BrowseCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WatchlistPage extends BasePage {

    @FindBy(css = ".clear-history")
    public WebElement clearAll;

    @FindBy(css = ".bookmarks-tab[href='/watchlist']")
    public WebElement watchListTab;

    @FindBy(css = ".watchlist-content-box > div")
    public Components<BrowseCard> cards;

    public WatchlistPage(WebDriver driver) {
        super(driver);
    }
}
