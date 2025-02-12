package Lilia_Rosca.LR_JUnit.NeonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LR_WatchlistTests extends LR_NeonStreamBaseTest{
// 10.02
    String moviePageUrl = "https://neon-stream--stg-g1xzvf5x.web.app/movie/912649";

    @Test
    @DisplayName("Watchlist page test")
    public void watchlistPageTest() {
        driver.get(moviePageUrl);
        moviePage.contentHeader.watchlistButton.add.click();
        header.watchlistTab.click();
        actions.waitForNumberOfElements(wlPage.cards, 1);
    }

    @Test
    @DisplayName("Watchlist state home page test")
    public void wlStateHomePageTest() {
        driver.get(moviePageUrl);
        moviePage.contentHeader.watchlistButton.add.click();
        header.homeTab.click();
        actions.waitForNumberOfElements(homePage.heroCarousel.placeHolders, 0);
        homePage.heroCarousel.tabs.get(4);
        actions.shouldBeDisplayed(homePage.heroCarousel.activeCard.watchlistButton.add); // ??? Denis remove - to check after git pull
        actions.scrollTo((homePage.singlePromoCards));
        var card = homePage.singlePromoCards.getFirst();
        actions.shouldBeDisplayed(card.watchlistButton.add);

    }

}