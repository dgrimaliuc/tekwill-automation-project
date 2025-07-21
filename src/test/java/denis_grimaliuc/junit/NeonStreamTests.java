package denis_grimaliuc.junit;

import denis_grimaliuc.neonStream.*;
import internal.BaseTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class NeonStreamTests extends BaseTest {
    NeonStreamHomePage home = new NeonStreamHomePage(driver);
    NeonStreamContentPage content = new NeonStreamContentPage(driver);
    NeonStreamWatchEpisodePage episode = new NeonStreamWatchEpisodePage(driver);

    @BeforeEach
    public void setup() {
        log.info("Opening the Neon Stream page");
        driver.get("https://neon-stream.web.app");
        actions.waitForNumberOfElements(home.placeholders, 0);
    }

    @Test
    public void clickOnHeroCardTitleTest() {
        home.heroCarousel.activeCard.title.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    public void waitForActiveHeroCardToChangeTest() {
        String fTabClass = home.heroCarousel.tabs.getFirst().getAttribute("class");
        assertThat(fTabClass, Matchers.containsString("is-active"));
        String fImage = home.heroCarousel.activeCard.picture.getAttribute("src");

        waitFor(10);
        String sTabClass = home.heroCarousel.tabs.get(1).getAttribute("class");
        assertThat(sTabClass, Matchers.containsString("is-active"));

        String sImage = home.heroCarousel.activeCard.picture.getAttribute("src");

        assertThat(fImage, not(equalTo(sImage)));
    }

    @Test
    public void watchNowHeroCardClickMovieTest() {
        home.heroCarousel.findContent("movie");
        home.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    public void watchNowHeroCardClickTvTest() {
        home.heroCarousel.findContent("tv");
        home.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLMatches(".*/tv/\\d+/watch/.*");
    }

    @Test
    public void leftRightArrowsClickTest() {
        String fImage = home.heroCarousel.activeCard.picture.getAttribute("src");
        home.heroCarousel.arrowLeft.click();
        Tab lastTab = home.heroCarousel.tabs.getLast();
        assertThat(lastTab.isActive(), equalTo(true));
        String sImage = home.heroCarousel.activeCard.picture.getAttribute("src");
        assertThat(fImage, not(equalTo(sImage)));

        home.heroCarousel.arrowRight.click();
        Tab firstTab = home.heroCarousel.tabs.getFirst();
        assertThat(firstTab.isActive(), equalTo(true));

        String tImage = home.heroCarousel.activeCard.picture.getAttribute("src");
        assertThat(fImage, equalTo(tImage));
    }

    @Test
    public void tabClickTest() {
        var index = 3;
        home.heroCarousel.tabs.get(index).click();
        String expectedImg = home.heroCarousel.cards.get(index).picture.getAttribute("src");
        String activeImage = home.heroCarousel.activeCard.picture.getAttribute("src");
        assertThat(activeImage, equalTo(expectedImg));
        assertThat(home.heroCarousel.tabs.get(index).isActive(), equalTo(true));
    }

    @Test
    public void addToWatchListHeroCardTest() {
        HeroCard heroCard = home.heroCarousel.cards.getFirst();
        heroCard.addToWatchlist.click();
        actions.shouldSee(heroCard.removeFromWatchlist);
        actions.shouldNotBeDisplayed(heroCard.addToWatchlist);

        actions.waitForNumberOfElements(home.watchlistCollection.cards, 1);
        String fWTitle = home.watchlistCollection.cards.getFirst().title.getText();
        assertThat(fWTitle, containsString(heroCard.title.getText()));
    }

    @Test
    public void watchlistTitleClickTest() {
        HeroCard heroCard = home.heroCarousel.cards.getFirst();
        heroCard.addToWatchlist.click();

        actions.waitForNumberOfElements(home.watchlistCollection.cards, 1);
        home.watchlistCollection.title.click();
        actions.waitForCurrentURLContains("/watchlist");
    }

    @Test
    public void collectionArrowsClickTest() {

        actions.scrollTo(home.upcomingMovies);
        home.upcomingMovies.arrowRight.click();
        actions.shouldHaveAttribute(home.upcomingMovies.cards.get(9), "inert", "false");
        actions.shouldHaveAttribute(home.upcomingMovies.cards.get(0), "inert", "true");
        home.upcomingMovies.arrowLeft.click();

        actions.shouldHaveAttribute(home.upcomingMovies.cards.get(9), "inert", "true");
        actions.shouldHaveAttribute(home.upcomingMovies.cards.get(0), "inert", "false");
    }

    @Test
    public void collectionCardClickTest() {

        actions.scrollTo(home.upcomingMovies);
        String expectedTitle = home.upcomingMovies.cards.getFirst().title.getText();
        home.upcomingMovies.cards.getFirst().click();
        actions.waitForCurrentURLContains("/movie/");
        actions.shouldHaveTextToBe(content.title, expectedTitle);
    }

    @Test
    public void singlePromoCardImageLeftClickTest() {
        actions.scrollTo(home.singlePromoCard);
        String expectedTitle = home.singlePromoCard.title.getText();
        home.singlePromoCard.imageLeft.click();
        actions.waitForCurrentURLContains("/movie/");
        actions.shouldHaveTextToBe(content.title, expectedTitle);
    }

    @Test
    public void singlePromoCardImageRightClickTest() {
        actions.scrollTo(home.singlePromoCard);
        String expectedTitle = home.singlePromoCard.title.getText();
        home.singlePromoCard.imageRight.click();
        actions.waitForCurrentURLContains("/movie/");
        actions.shouldHaveTextToBe(content.title, expectedTitle);
    }

    @Test
    public void singlePromoCardTitleClickTest() {
        actions.scrollTo(home.singlePromoCard);
        String expectedTitle = home.singlePromoCard.title.getText();
        home.singlePromoCard.title.click();
        actions.waitForCurrentURLContains("/movie/");
        actions.shouldHaveTextToBe(content.title, expectedTitle);
    }

    @Test
    public void singlePromoCardWatchNowClickTest() {
        actions.scrollTo(home.singlePromoCard);
        String expectedTitle = home.singlePromoCard.title.getText();
        home.singlePromoCard.watchNowButton.click();
        actions.waitForCurrentURLContains("/movie/");
        actions.shouldHaveTextToBe(content.title, expectedTitle);
    }

    @Test
    public void singlePromoCardWatchListClickTest() {
        actions.scrollTo(home.singlePromoCard);
        String expectedTitle = home.singlePromoCard.title.getText();
        home.singlePromoCard.addToWatchlist.click();
        actions.shouldSee(home.singlePromoCard.removeFromWatchlist);
        actions.shouldNotBeDisplayed(home.singlePromoCard.addToWatchlist);

        actions.scrollTo(home.watchlistCollection);
        actions.waitForNumberOfElements(home.watchlistCollection.cards, 1);
        String fWTitle = home.watchlistCollection.cards.getFirst().title.getText();
        assertThat(fWTitle, containsString(expectedTitle));
    }

    @Test
    public void singleShowCardImageClickTest() {
        actions.scrollTo(home.singleShowCard);
        actions.waitForNumberOfElements(home.singleShowCard.placeholders, 0);
        String expectedTitle = home.singleShowCard.title.getText()
                .replaceAll("\\(\\d+\\)", "").trim();

        home.singleShowCard.image.click();
        actions.waitForCurrentURLContains("/tv/");
        actions.shouldHaveTextContains(content.title, expectedTitle);
    }

    @Test
    public void singleShowCardTitleClickTest() {
        actions.scrollTo(home.singleShowCard);
        actions.waitForNumberOfElements(home.singleShowCard.placeholders, 0);
        String expectedTitle = home.singleShowCard.title.getText()
                .replaceAll("\\(\\d+\\)", "").trim();

        home.singleShowCard.title.click();
        actions.waitForCurrentURLContains("/tv/");
        actions.shouldHaveTextContains(content.title, expectedTitle);
    }

    @Test
    public void singleShowCardWatchNowClickTest() {
        actions.scrollTo(home.singleShowCard);
        actions.waitForNumberOfElements(home.singleShowCard.placeholders, 0);
        String expectedTitle = home.singleShowCard.title.getText()
                .replaceAll("\\(\\d+\\)", "").trim();

        home.singleShowCard.watchNowButton.click();
        actions.waitForCurrentURLContains("/watch/1/1");
        actions.shouldHaveTextContains(episode.title, expectedTitle.toUpperCase());
    }
}
