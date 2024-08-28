package denis_grimaliuc.junit.neonStream;

import denis_grimaliuc.ui.neonStream.pages.NeonStreamHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class NeonStreamSmokeTests extends NeonStreamBaseTest {

    @BeforeEach
    public void waitForPageToLoad() {
        actions.shouldNotSee(neonStreamPage.heroCarousel.placeholder);
        waitFor(2);
        neonStreamPage = new NeonStreamHomePage(driver);
    }

    @Test
    @DisplayName("Content is displayed test")
    public void contentIsDisplayedTest() {
        assertThat(neonStreamPage.heroCarousel.cards.get(0).title.isDisplayed(), equalTo(true));
        assertThat(neonStreamPage.heroCarousel.cards.get(0).description.isDisplayed(), equalTo(true));
        assertThat(neonStreamPage.heroCarousel.cards.get(0).watchlistButton.isDisplayed(), equalTo(true));
        assertThat(neonStreamPage.heroCarousel.cards.get(0).watchNowButton.isDisplayed(), equalTo(true));
        actions.shouldSee(neonStreamPage.heroCarousel.cards.get(0).backgroundImage);
    }

    @Test
    @DisplayName("Left arrow click test")
    public void leftArrowClickTest() {
        int index1 = 2;

        neonStreamPage.heroCarousel.tabs.get(index1).click();
        String title = neonStreamPage.heroCarousel.cards.get(index1).title.getText();
        String description = neonStreamPage.heroCarousel.cards.get(index1).description.getText();
        String image = neonStreamPage.heroCarousel.cards.get(index1).backgroundImage.getAttribute("src");

        neonStreamPage.heroCarousel.leftArrow.click();
        actions.shouldNotHaveTextToBe(neonStreamPage.heroCarousel.cards.get(index1 - 1).title, title);
        actions.shouldNotHaveTextToBe(neonStreamPage.heroCarousel.cards.get(index1 - 1).description, description);
        actions.shouldNotHaveAttribute(neonStreamPage.heroCarousel.cards.get(index1 - 1).backgroundImage, "src", image);
    }

    @Test
    @DisplayName("Right Arrow test")
    public void rightArrowClickTest() {
        int index1 = 1;

        neonStreamPage.heroCarousel.tabs.get(index1).click();
        String title = neonStreamPage.heroCarousel.cards.get(index1).title.getText();
        String description = neonStreamPage.heroCarousel.cards.get(index1).description.getText();
        String image = neonStreamPage.heroCarousel.cards.get(index1).backgroundImage.getAttribute("src");

        neonStreamPage.heroCarousel.leftArrow.click();
        actions.shouldNotHaveTextToBe(neonStreamPage.heroCarousel.cards.get(index1 + 1).title, title);
        actions.shouldNotHaveTextToBe(neonStreamPage.heroCarousel.cards.get(index1 + 1).description, description);
        actions.shouldNotHaveAttribute(neonStreamPage.heroCarousel.cards.get(index1 + 1).backgroundImage, "src", image);
    }

    @Test
    @DisplayName("Watch now Movie test")
    public void watchNowMovieTest() {
        var index = neonStreamPage.heroCarousel.findHeroCard("movie");
        neonStreamPage.heroCarousel.cards.get(index).watchNowButton.click();
        wait.until(urlContains("/movie/"));
    }

    @Test
    @DisplayName("Watch now TV test")
    public void watchNowTVTest() {
        var index = neonStreamPage.heroCarousel.findHeroCard("tv");
        neonStreamPage.heroCarousel.cards.get(index).watchNowButton.click();
        wait.until(urlContains("/tv/"));
    }

    @Test
    @DisplayName("Click on Add to WL movie test")
    public void addToWLMovieTest() {
        var index = neonStreamPage.heroCarousel.findHeroCard("movie");
        String expectedTitle = neonStreamPage.heroCarousel.cards.get(index).title.getText();
        neonStreamPage.heroCarousel.cards.get(index).watchlistButton.click();

        actions.shouldSee(neonStreamPage.watchlistCollection);
        actions.shouldHaveTextToBe(neonStreamPage.watchlistCollection.titles.get(0), expectedTitle);
    }
}
