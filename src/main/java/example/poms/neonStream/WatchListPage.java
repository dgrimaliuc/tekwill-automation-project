package example.poms.neonStream;

import example.components.neonStream.BrowseCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WatchListPage extends BasePage {

    @FindBy(css = ".clear-history")
    public WebElement clearAll;

    @FindBy(css = ".bookmarks-tab[href='/watchlist']")
    public WebElement watchListTab;

    @FindBy(css = ".watchlist-content-box > div")
    public Components<BrowseCard> cards;

    public WatchListPage(WebDriver driver) {
        super(driver);
    }
}
