package Magda_Petrachi.NeoStreamJUnit;

import Magda_Petrachi.NeonStream.*;
import Magda_Petrachi.NeonStream.NeoStreamPage;
import example.components.neonStream.HeroCard;
import example.components.neonStream.Tab;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
    public void waitForActiveCardToChangeTest() {
        String fTabClass = home.heroCarousel.tabs.getFirst().getAttribute("class");
        String fTabClass = page.heroCarousel.tabs.getFirst().getAttribute("class");
        assertThat(fTabClass, containsString("is-active"));
        String fImage = home.heroCarousel.activeCard.image.getAttribute("src");
        String fImage = page.heroCarousel.activeCard.image.getAttribute("src");

        waitFor(10);

        String sTabClass = home.heroCarousel.tabs.get(1).getAttribute("class");
        String sTabClass = page.heroCarousel.tabs.get(1).getAttribute("class");
        assertThat(sTabClass, containsString("is-active"));

        String sImage = home.heroCarousel.activeCard.image.getAttribute("scr");
        String sImage = page.heroCarousel.activeCard.image.getAttribute("scr");
        assertThat(fImage, not(equals(sImage)));
    }

    @Test
    public void watchMovieHeroCardClickTVTest() {
        home.heroCarousel.activeCard.watchNow.click();
        page.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/tv/");
    }

    @@ -70,162 +66,34 @@ public void watchMovieHeroCardClickTVTest() {

        @Test
        public void leftArrowsClickTest() {
            home.heroCarousel.leftArrow.click();
            Tab lastTab = home.heroCarousel.tabs.getLast();
            page.heroCarousel.leftArrow.click();
            Tab lastTab = page.heroCarousel.tabs.getLast();
            assertThat(String.valueOf(lastTab.isActive()), equalTo(true));
        }

        @Test
        public void tabClickTest() {
            var index = 3;
            home.heroCarousel.tabs.get(index).click();
            String expImage = home.heroCarousel.cards.get(index).image.getAttribute("scr");
            String activeImg = home.heroCarousel.activeCard.image.getAttribute("scr");
            page.heroCarousel.tabs.get(index).click();
            String expImage = page.heroCarousel.cards.get(index).image.getAttribute("scr");
            String activeImg = page.heroCarousel.activeCard.image.getAttribute("scr");
            assertThat(activeImg, equalTo(expImage));
            assertThat(home.heroCarousel.tabs.get(index).isActive(), equalTo(true));
            assertThat(page.heroCarousel.tabs.get(index).isActive(), equalTo(true));

        }

        @Test
        public void addToWatchListHeroCardTest() {
            NeonStreamHeroCard heroCard = home.heroCarousel.cards.getFirst();
            HeroCard heroCard = page.heroCarousel.cards.getFirst();
            heroCard.watchListButton.click();
//            actions.shouldSee(heroCard.removeFromWatchlist);
            actions.shouldNotBeDisplayed(heroCard.watchListButton);

            actions.waitForNumberOfElements(home.watchListCollection.cards, 1);
            String fWhachListTitle = home.watchListCollection.cards.getFirst().title.getText();
            actions.waitForNumberOfElements(page.watchListCollection.cards, 1);
            String fWhachListTitle = page.watchListCollection.cards.getFirst().title.getText();

            assertThat(fWhachListTitle, containsString(heroCard.title.getText()));
        }

//    @Test
//    public void whachlistTitleClickTest() {
//        NeoStreamHeroCarousel heroCard = home.heroCarousel.cards.getFirst();
//        heroCard.addToWatchlist.click();
//
//        actions.waitForNumberOfElements(home.watchListCollection.cards, 1);
//        actions.waitForCurrentURLContains("/whatchlist/");
//    }

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

//    @Test
//    public void browsePageSmokeTest() {
//        home.browseButton.click();
//        actions.shouldSee(browse.title);
//        actions.shouldSee(browse.browseActions);
//        actions.waitForNumberOfElementsToBeMoreThan(browse.browseCard, 1);
//        browse.browseCard.click();
//        actions.waitForCurrentURLContains(".*movie|tv.*");
//    }

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
            actions.searchInput.click();
            actions.searchInput.sendKeys(query);
            actions.waitForNumberOfElementsToBeMoreThan(search.cards, 5);
            int currentlyDisplayed = search.cards.size();
            actions.scrollTo(search.cards.getLast());
            actions.waitForNumberOfElements(search.cards, currentlyDisplayed);

        }

    }
