package denis_grimaliuc.junit.neonStream;

import denis_grimaliuc.ui.neonStream.components.HeroCarousel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class NeonStreamSmokeTests extends NeonStreamBaseTest {


    HeroCarousel carousel = neonStreamPage.heroCarousel;

    @BeforeEach
    public void waitForPageToLoad() {
        actions.shouldNotSee(neonStreamPage.heroCarousel.placeholder);
        actions.shouldSee(carousel);
    }

    @Test
    @DisplayName("Content is displayed test")
    public void contentIsDisplayedTest() {

        actions.shouldSee(carousel.cards.get(0).title);
        actions.shouldSee(carousel.cards.get(0).description);
        actions.shouldSee(carousel.cards.get(0).watchlistButton);
        actions.shouldSee(carousel.cards.get(0).watchNowButton);
        actions.shouldSee(carousel.cards.get(0).backgroundImage);
    }

    @Test
    @DisplayName("Left arrow click test")
    public void leftArrowClickTest() {
        int index1 = 2;

        carousel.tabs.get(index1).click();
        String title = carousel.cards.get(index1).title.getText();
        String description = carousel.cards.get(index1).description.getText();
        String image = carousel.cards.get(index1).backgroundImage.getAttribute("src");

        carousel.leftArrow.click();
        actions.shouldNotHaveTextToBe(carousel.cards.get(index1 - 1).title, title);
        actions.shouldNotHaveTextToBe(carousel.cards.get(index1 - 1).description, description);
        actions.shouldNotHaveAttribute(carousel.cards.get(index1 - 1).backgroundImage, "src", image);
    }

    @Test
    @DisplayName("Right Arrow test")
    public void rightArrowClickTest() {
        int index1 = 1;


        carousel.tabs.get(index1).click();
        String title = carousel.cards.get(index1).title.getText();
        String description = carousel.cards.get(index1).description.getText();
        String image = carousel.cards.get(index1).backgroundImage.getAttribute("src");

        carousel.leftArrow.click();
        actions.shouldNotHaveTextToBe(carousel.cards.get(index1 + 1).title, title);
        actions.shouldNotHaveTextToBe(carousel.cards.get(index1 + 1).description, description);
        actions.shouldNotHaveAttribute(carousel.cards.get(index1 + 1).backgroundImage, "src", image);
    }

    @Test
    @DisplayName("Watch now Movie test")
    public void watchNowMovieTest() {
        var index = carousel.findHeroCard("movie");
        carousel.cards.get(index).watchNowButton.click();
        wait.until(urlContains("/movie/"));
    }

    @Test
    @DisplayName("Watch now TV test")
    public void watchNowTVTest() {
        var index = carousel.findHeroCard("tv");
        carousel.cards.get(index).watchNowButton.click();
        wait.until(urlContains("/tv/"));
    }

    @Test
    @DisplayName("Click on Add to WL movie test")
    public void addToWLMovieTest() {

        var index = carousel.findHeroCard("movie");
        String expectedTitle = carousel.cards.get(index).title.getText();
        carousel.cards.get(index).watchlistButton.click();

        actions.shouldSee(neonStreamPage.watchlistCollection);
        actions.shouldHaveTextToBe(neonStreamPage.watchlistCollection.titles.get(0), expectedTitle);
    }
}
