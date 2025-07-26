package lilia_toma.junit;

import internal.BaseTest;
import lilia_toma.NeonStream.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NeonStreamTests extends BaseTest {
    NeonStreamHomePage page = new NeonStreamHomePage(driver);

    @BeforeEach
    public void setup() {
        log.info("Opening the Neo Stream page");
        driver.get("https://neon-stream.web.app/");
        actions.waitForNumberOfElements(page.placeholders, 0);
    }

    @Test
    public void clickOnHeroCardTitleTest() {
       page.heroCarousel.activeCard.title.click();
       actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    public void waitForActiveHeroCardToChangeTest() {
       String fTabClass = page.heroCarousel.tabs.getFirst().getAttribute("class");
       assertThat(fTabClass, Matchers.containsString("is-active"));
       String fImage = page.heroCarousel.activeCard.picture.getAttribute("src");
       waitFor(10);

        String sTabClass = page.heroCarousel.tabs.get(1).getAttribute("class");
        assertThat(sTabClass, Matchers.containsString("is-active"));

        String sImage = page.heroCarousel.activeCard.picture.getAttribute("src");

        assertThat(fImage, CoreMatchers.not(equalTo(sImage)));
    }

    @Test
    public void watchNowHeroCardClickMovieTest() {
        page.heroCarousel.findContent("movie");
        page.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    public void watchNowHeroCardClickTvTest() {
        page.heroCarousel.findContent("tv");
        page.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLMatches(".*/tv/\\d+/watch/.*");
    }

    @Test
    public void addToWatchlistHeroCardTVTest() {
        page.heroCarousel.findContent("tv");
        page.heroCarousel.activeCard.addToWatchList.click();
        actions.waitForCurrentURLMatches("");
    }

    @Test
    public void addToWatchlistHeroCardTest() {
//        page.heroCarousel.findContent("");
//        page.heroCarousel.activeCard.addToWatchList.click();
//        actions.waitForCurrentURLMatches("");
        HeroCard heroCard = page.heroCarousel.cards.getFirst();
        heroCard.addToWatchList.click();
        actions.shouldSee(heroCard.removeFromWatchList);
        actions.shouldNotBeDisplayed(heroCard.addToWatchList);

        actions.waitForNumberOfElements(page.watchlistCollection.cards, 1);
        String fWTitle = page.watchlistCollection.cards.getFirst().title.getText();

        assertThat(fWTitle, CoreMatchers.containsString(heroCard.title.getText()));
    }

    @Test
    public void leftRightArrowsClickTest() {
        String fImage = page.heroCarousel.activeCard.picture.getAttribute("src");
        page.heroCarousel.arrowLeft.click();
        Tab lastTab = page.heroCarousel.tabs.getLast();
        assertThat(lastTab.isActive(), equalTo(true));
        String sImage = page.heroCarousel.activeCard.picture.getAttribute("src");
        assertThat(fImage, CoreMatchers.not(equalTo(sImage)));

        page.heroCarousel.arrowRight.click();
        Tab firstTab = page.heroCarousel.tabs.getFirst();
        assertThat(firstTab.isActive(), equalTo(true));

        String tImage = page.heroCarousel.activeCard.picture.getAttribute("src");
        assertThat(fImage, CoreMatchers.equalTo(tImage));
    }

    @Test
    public void tabClickTest() {
        var index = 3;
        page.heroCarousel.tabs.get(index).click();
        String expectedImg = page.heroCarousel.cards.get(index).picture.getAttribute("src");
        String activeImage = page.heroCarousel.activeCard.picture.getAttribute("src");
        assertThat(activeImage, CoreMatchers.equalTo(expectedImg));

        assertThat(page.heroCarousel.tabs.get(index).isActive(), CoreMatchers.equalTo(true));
    }

    @Test
    public void upcomingMoviesHeroCardTest() {
        BrowseCard browseCard = page.upcomingMovies.cards.getFirst();
        actions.scrollTo(page.upcomingMovies.cards);
        actions.waitForNumberOfElements(page.placeholders, 0);
        String firstMovieCard = browseCard.title.getText().trim();
        System.out.println("The last upcoming movie is: " + firstMovieCard);


    }

}
