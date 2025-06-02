package example.junit.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WatchListTest extends NeonStreamBaseTest {
    String moviePageUrl = "https://neon-stream--stg-g1xzvf5x.web.app/movie/912649";
    String seriesUrl = "https://neon-stream.web.app/tv/1396";

    @Test
    @DisplayName("Watchlist page test")
    public void watchListPageTest() {
        driver.get(moviePageUrl);
        moviePage.contentHeader.watchListButton.add.click();
        header.watchListTab.click();
        actions.waitForNumberOfElements(wlPage.cards, 1);
    }

    @Test
    @DisplayName("Watch list state home page test")
    public void wlStateHomePageTest() {
        driver.get(moviePageUrl);
        moviePage.contentHeader.watchListButton.add.click();
        header.homeTab.click();
        actions.waitForNumberOfElements(homePage.heroCarousel.placeholders, 0);
        homePage.heroCarousel.tabs.get(4).click();
        actions.shouldBeDisplayed(homePage.heroCarousel.activeCard.watchListButton.remove);
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        actions.shouldBeDisplayed(card.removeFromWatchlist);
    }

    @Test
    @DisplayName("Series add to watch list test")
    public void seriesAddToWatchListTest() {
        driver.get(seriesUrl);
        seriesPage.contentHeader.watchListButton.add.click();
        header.homeTab.click();
        actions.waitForNumberOfElements(homePage.heroCarousel.placeholders, 0);
        actions.waitForNumberOfElements(homePage.watchListCarousel.cards, 1);
        actions.scrollTo(homePage.singleCard);
        actions.shouldBeDisplayed(homePage.singleCard.watchListButton.remove);
        header.watchListTab.click();
        actions.waitForNumberOfElements(wlPage.cards, 1);
    }

}
