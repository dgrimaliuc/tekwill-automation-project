package Lilia_Rosca.poms.LR_NeonStream;

import Lilia_Rosca.components.LR_NeonStream.LR_BrowseCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_WatchListPage extends BasePage {
// 10.02
    @FindBy(css = ".clear-history")
    public WebElement clearAll;

    @FindBy(css =".bookmarks-tab[href ='/watchlist']")
    public WebElement watchListTab;

    @FindBy(css = ".watchlist-content-box > div")
    public Components<LR_BrowseCard> cards;

    public LR_WatchListPage(WebDriver driver) {
        super(driver);
    }
}
