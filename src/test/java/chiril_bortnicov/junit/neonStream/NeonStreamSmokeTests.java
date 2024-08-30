package chiril_bortnicov.junit.neonStream;

import chiril_bortnicov.ui.neonStream.components.HeroCarousel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class NeonStreamSmokeTests extends NeonStreamBaseTest {

    HeroCarousel heroCarousel = neonStreamPage.heroCarousel;

    @BeforeEach
    public void waitForPageToLoad() {
        actions.shouldNotSee(neonStreamPage.heroCarousel.placeholder);
        actions.shouldSee(heroCarousel);
    }

    @Test
    @DisplayName("Content is displayed test")
    public void contentIsDisplayedTest() {
        var card = heroCarousel.cards.get(0);
        assertThat(card.title.isDisplayed(), equalTo(true));
        assertThat(card.description.isDisplayed(), equalTo(true));
        assertThat(card.watchlistButton.isDisplayed(), equalTo(true));
        assertThat(card.watchNowButton.isDisplayed(), equalTo(true));
        actions.shouldSee(card.backgroundImage);
    }

    @Test
    @DisplayName("Left arrow click test")
    public void leftArrowClickTest() {
        int index1 = 2;
        heroCarousel.tabs.get(index1).click();
        String title = heroCarousel.cards.get(index1).title.getText();
        String description = heroCarousel.cards.get(index1).description.getText();
        String image = heroCarousel.cards.get(index1).backgroundImage.getAttribute("src");
        heroCarousel.leftArrow.click();
        actions.shouldNotHaveTextToBe(heroCarousel.cards.get(index1 - 1).title, title);
        actions.shouldNotHaveTextToBe(heroCarousel.cards.get(index1 - 1).description, description);
        actions.shouldNotHaveAttribute(heroCarousel.cards.get(index1 - 1).backgroundImage, "src", image);
    }

    @Test
    @DisplayName("Light arrow click test")
    public void rightArrowClickTest() {
        int index1 = 2;
        heroCarousel.tabs.get(index1).click();
        String title = heroCarousel.cards.get(index1).title.getText();
        String description = heroCarousel.cards.get(index1).description.getText();
        String image = heroCarousel.cards.get(index1).backgroundImage.getAttribute("src");
        heroCarousel.leftArrow.click();
        actions.shouldNotHaveTextToBe(heroCarousel.cards.get(index1 + 1).title, title);
        actions.shouldNotHaveTextToBe(heroCarousel.cards.get(index1 + 1).description, description);
        actions.shouldNotHaveAttribute(heroCarousel.cards.get(index1 + 1).backgroundImage, "src", image);
    }

    @Test
    @DisplayName("Watch now movie test")
    public void watchNowMovieTest() {
        var index = heroCarousel.findHeroCard("movie");
        heroCarousel.cards.get(index).watchNowButton.click();
        wait.until(urlContains("/movie/"));
    }

    @Test
    @DisplayName("Watch now TV test")
    public void watchNowTVTest() {
        var index = heroCarousel.findHeroCard("tv");
        heroCarousel.cards.get(index).watchNowButton.click();
        wait.until(urlContains("/tv/"));
    }

    @Test
    @DisplayName("Click on Add to WL movie test")
    public void addToWLMovieTest() {
        var index = heroCarousel.findHeroCard("movie");
        String expectedTitle = heroCarousel.cards.get(index).title.getText();
        heroCarousel.cards.get(index).watchlistButton.click();

        actions.shouldSee(neonStreamPage.watchlistCollection);
        actions.shouldHaveTextToBe(neonStreamPage.watchlistCollection.titles.get(0), expectedTitle);
    }
}
