package Magda_Petrachi.NeoStreamJUnit;

import Magda_Petrachi.NeonStream.*;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class NeoStreamTests extends BaseTest {

    NeoStreamPage home = new NeoStreamPage(driver);
    ContentPage content = new ContentPage(driver);
    WatchEpisodPage episode = new WatchEpisodPage(driver);
    BrowsePage browse = new BrowsePage(driver);
    SearchPage search = new SearchPage(driver);
    NeoStreamPage page = new NeoStreamPage(driver);

    @BeforeEach
    void setup() {
        log.info("Open Neo Stream page");
        driver.get("https://neon-stream.web.app/");
        actions.waitForNumberOfElements(home.placeholders, 0);
        actions.waitForNumberOfElements(page.placeholders, 0);
    }

    @Test
    public void smokeTestHeroCarusel() {
        actions.shouldSee(home.heroCarousel.cards.getFirst().title);
        actions.shouldSee(page.heroCarousel.cards.getFirst().title);
    }

    @Test
    public void clickOnHeroCardTitle() {
        home.heroCarousel.activeCard.title.click();
        page.heroCarousel.activeCard.title.click();
        actions.waitForCurrentURLContains("/movie/");
    }


    @Test
    public void watchMovieHeroCardClickTVTest() {
        home.heroCarousel.activeCard.watchNow.click();
        page.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/tv/");
    }


    @Test
    public void arrowColectionClikTest() {
        actions.scrollTo(home.upcomingMovies);
        home.upcomingMovies.arrowRight.click();
        actions.shouldHaveAttribute(home.upcomingMovies.cards.get(9), "inert", "false");
        actions.shouldHaveAttribute(home.upcomingMovies.cards.get(0), "inert", "true");

        home.upcomingMovies.arrowLeft.click();
        actions.shouldHaveAttribute(home.upcomingMovies.cards.get(9), "inert", "true");
        actions.shouldHaveAttribute(home.upcomingMovies.cards.get(0), "inert", "false");
    }

    @Test
    public void colectionCardClickTest() {
        actions.scrollTo(home.upcomingMovies);
        String expectedTitle = home.upcomingMovies.cards.getFirst().title.getText();
        home.upcomingMovies.cards.getFirst().click();
        actions.waitForCurrentURLContains("/movie/");
        actions.shouldHaveTextToBe(content.title, expectedTitle);
    }

    @Test
    public void simpleShowCardImageLeftClickTest() {
        actions.scrollTo(home.simplePromoCard);
        String expectedTitle = home.simplePromoCard.title.getText();
        home.simplePromoCard.imageLeft.click();
        actions.waitForCurrentURLContains("/movie/");
        actions.shouldHaveTextToBe(content.title, expectedTitle);

    }

    @Test
    public void singlePromoCardWatchlistClickTest() {
        actions.scrollTo(home.simplePromoCard);
        String expectedTitle = home.simplePromoCard.title.getText();
        home.simplePromoCard.addToWatchlist.click();
        actions.shouldSee(home.simplePromoCard.removeFromWatchlist);
        actions.shouldNotBeDisplayed(home.simplePromoCard.addToWatchlist);

        actions.scrollTo(home.watchListCollection);
        actions.waitForNumberOfElements(home.watchListCollection.cards, 1);
        String fWatchlistTitle = home.watchListCollection.cards.getFirst().title.getText();
        assertThat(fWatchlistTitle, containsString(expectedTitle));
    }


    @Test
    public void simpleShowCardImageClickTest() {
        actions.scrollTo(home.simpleShowCard);
        actions.waitForNumberOfElements(home.simpleShowCard.placeholders, 0);
        String expectedTitle = home.simpleShowCard.title.getText()
                .replaceAll("\\(\\d+\\)", "").trim();

        home.simpleShowCard.image.click();
        actions.waitForCurrentURLContains("/watch/1/1");
        actions.shouldHaveTextToBe(content.title, expectedTitle.toUpperCase());

    }

    @Test
    public void simpleShowCardWatchNowClickTest() {
        actions.scrollTo(home.simpleShowCard);
        actions.waitForNumberOfElements(home.simpleShowCard.placeholders, 0);
        String expectedTitle = home.simpleShowCard.title.getText()
                .replaceAll("\\(\\d+\\)", "").trim();

        home.simpleShowCard.watchNowButton.click();
        actions.waitForCurrentURLContains("/watch/1/1");
        actions.shouldHaveTextToBe(content.title, expectedTitle.toUpperCase());

    }


    @Test
    public void searchPageSmokeTest() {
        search.searchInput.click();
        actions.shouldHaveTextToBe(search.noResultsSearch.title, "Ready to dive in?");
        actions.shouldHaveTextToBe(search.noResultsSearch.description, "Start your search to explore a world of amazing content. ");
        actions.shouldSee(search.noResultsSearch.stars);

    }

    @Test
    public void searchQueryParamTest() {
        String query = "The";
        driver.get("https://neon-stream.web.app/search?q=" + query);
        actions.shouldHaveAttribute(search.searchInput, "value", query);
        search.shoudSeeResultsFor(query);
    }

    @Test
    public void searchByTitleTest() {
        String query = "Naturo";
        search.searchInput.click();
        search.searchInput.sendKeys(query);
        search.shoudSeeResultsFor(query);
    }

    @Test
    public void moreSearchResultTest() {
        String query = "The";
        search.searchInput.click();
        search.searchInput.sendKeys(query);
        actions.waitForNumberOfElementsToBeMoreThan(search.cards, 5);
        int currentlyDisplayed = search.cards.size();
        actions.scrollTo(search.cards.getLast());
        actions.waitForNumberOfElements(search.cards, currentlyDisplayed);

    }

}
