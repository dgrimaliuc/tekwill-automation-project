package example.junit.neonStream;

import example.ui.neonStream.components.HeroCarousel;
import org.junit.jupiter.api.Assertions;
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

    @Test
    @DisplayName("Click on Add to TV movie test")
    public void addToWLTvTest() {

        var index = carousel.findHeroCard("tv");
        String expectedTitle = carousel.cards.get(index).title.getText();
        carousel.cards.get(index).watchlistButton.click();

        actions.shouldSee(neonStreamPage.watchlistCollection);
        actions.shouldHaveTextToBe(neonStreamPage.watchlistCollection.titles.get(0), expectedTitle);
    }

    @Test
    @DisplayName("Select tab by index test")
    public void selectTabByIndexTest() {
        carousel.tabs.get(2).click();
        Assertions.assertTrue(carousel.tabs.get(2).isActive(), "Verify that 3-th tab is active");
        Assertions.assertFalse(carousel.tabs.get(0).isActive(), "Verify that 1-th tab is NOT active");
    }

    @Test
    @DisplayName("TAB timeout test")
    public void tabTimeoutTest() {
        Assertions.assertTrue(carousel.tabs.get(0).isActive(), "Verify that 1-th tab is active");
        actions.shouldHaveAttributeContains(carousel.tabs.get(1), "class", "is-active");
        Assertions.assertFalse(carousel.tabs.get(0).isActive(), "Verify that 0-th tab is not active");
    }

    @Test
    @DisplayName("Reverse click on left arrow")
    public void reverseClickOnLeftArrow() {
        int count = carousel.tabs.size();
        carousel.leftArrow.click();
        Assertions.assertTrue(carousel.tabs.get(count - 1).isActive(), "Verify that last tab is active");
        Assertions.assertFalse(carousel.tabs.get(0).isActive(), "Verify that 0-th tab is not active");
    }

    @Test
    @DisplayName("Reverse click on right arrow")
    public void reverseClickOnRightArrow() {
        int count = carousel.tabs.size();

        carousel.tabs.get(count - 1).click();
        carousel.rightArrow.click();
        Assertions.assertFalse(carousel.tabs.get(count - 1).isActive(), "Verify that last tab is not active");
        Assertions.assertTrue(carousel.tabs.get(0).isActive(), "Verify that 0-th tab is active");
    }

    @Test
    @DisplayName("test Scroll")
    public void testScroll() {
        actions.scrollTo(neonStreamPage.singleCard);

    }
}
