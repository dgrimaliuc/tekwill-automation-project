package IonErm.junit.neon_app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WatchListTest extends NeonAppBaseTest {
    String venomPage = "https://neon-stream--stg-g1xzvf5x.web.app/movie/912649";
    String bBadPage = "https://neon-stream--stg-g1xzvf5x.web.app/tv/1396";
    String attackOnTPage = "https://neon-stream--stg-g1xzvf5x.web.app/tv/1429";

    @Test
    @DisplayName("Watchlist page test")
    public void watchListTest() {
        driver.get(venomPage);
        moviePage.contentHeader.watchListButton.add.click();
        header.watchlistTab.click();
        actions.waitForNumberOfElements(wlPage.cards, 1);
        System.out.println("o mers");
    }

    @Test
    @DisplayName("Watchlist state home page test")
    public void watchListStateHomePageTest() {
        driver.get(venomPage);
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
    @DisplayName("Series add to watchlist test")
    public void seriesAddToWatchlistTest() {
        driver.get(bBadPage);
        seriesPage.contentHeader.watchListButton.add.click();
        header.homeTab.click();
        actions.waitForNumberOfElements(homePage.heroCarousel.placeholders, 0);
        actions.waitForNumberOfElements(homePage.watchListCarousel.cards, 1);
        actions.scrollTo(homePage.singleCards);
        actions.shouldBeDisplayed(homePage.singleCards.watchListButton.remove);
        header.watchlistTab.click();
        actions.waitForNumberOfElements(wlPage.cards, 1);
    }

    @Test
    @DisplayName("AoT add to watchlist test")
    public void aotAddToWatchlistTest() {
        driver.get(attackOnTPage);
        seriesPage.backPicture.isDisplayed();
        seriesPage.contentHeader.title.isDisplayed();
        seriesPage.contentHeader.watchListButton.add.click();
        seriesPage.contentHeader.watchNow.click();
        header.homeTab.click();
        actions.waitForNumberOfElements(homePage.heroCarousel.placeholders, 0);
        actions.waitForNumberOfElements(homePage.watchListCarousel.cards, 1);
        header.watchlistTab.click();
        actions.waitForNumberOfElements(wlPage.cards, 1);
    }
}
