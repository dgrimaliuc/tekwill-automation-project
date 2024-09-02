package IgorTabirta.Junit.Neon;

import IgorTabirta.UI.Neon.Component.HeroCarousel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class NeonSmokeTest extends NeonBaseTest {

    HeroCarousel heroCarousel = neonPage.heroCarousel;

    @BeforeEach
    public void waitPageToLoad() {
        actions.shouldNotSee(heroCarousel.placeHolder);
        waitFor(2);
    }

    @Test
    @DisplayName("Content is displayed test")
    public void contentIsDisplayedTest() {


        assertThat(heroCarousel.cards.get(0).title.isDisplayed(), equalTo(true));
        assertThat(heroCarousel.cards.get(0).description.isDisplayed(), equalTo(true));
        assertThat(heroCarousel.cards.get(0).watchlistButton.isDisplayed(), equalTo(true));
        assertThat(heroCarousel.cards.get(0).watchNowButton.isDisplayed(), equalTo(true));
        //wait.until(driver -> heroCarousel.cards.get(0).backgroundImage.isDisplayed());
        actions.shouldSee(heroCarousel.cards.get(0).backgroundImage);

    }

    @Test
    @DisplayName("Left Arrow click Test")
    public void leftArrowClickTest() {
        int index = 2;
        heroCarousel.tabs.get(index).click();
        String title = heroCarousel.cards.get(index).title.getText();
        String description = heroCarousel.cards.get(index).description.getText();
        String image = heroCarousel.cards.get(index).backgroundImage.getAttribute("src");

        heroCarousel.leftArrow.click();
        actions.shouldNotHaveTextToBe(heroCarousel.cards.get(index - 1).title, title);
        actions.shouldNotHaveTextToBe(heroCarousel.cards.get(index - 1).description, description);
        actions.shouldNotHaveAttribute(heroCarousel.cards.get(index - 1).backgroundImage, "src", image);

    }

    @Test
    @DisplayName("Right Arrow click Test")
    public void rightArrowClickTest() {
        int index = 1;
        heroCarousel.tabs.get(index).click();
        String title = heroCarousel.cards.get(index).title.getText();
        String description = heroCarousel.cards.get(index).description.getText();
        String image = heroCarousel.cards.get(index).backgroundImage.getAttribute("src");

        heroCarousel.leftArrow.click();
        actions.shouldNotHaveTextToBe(heroCarousel.cards.get(index + 1).title, title);
        actions.shouldNotHaveTextToBe(heroCarousel.cards.get(index + 1).description, description);
        actions.shouldNotHaveAttribute(heroCarousel.cards.get(index + 1).backgroundImage, "src", image);

    }

    @Test
    @DisplayName("Watch now movie test")
    public void WatchNowMovieTest() {
        var indexCard = neonPage.heroCarousel.findHeroCard("movie");
        neonPage.heroCarousel.cards.get(indexCard).watchNowButton.click();
        wait.until(urlContains("/movie/"));

    }

    @Test
    @DisplayName("Watch now TV test")
    public void WatchNowTVTest() {
        var indexCard = neonPage.heroCarousel.findHeroCard("tv");
        neonPage.heroCarousel.cards.get(indexCard).watchNowButton.click();
        wait.until(urlContains("/tv/"));

    }

    @Test
    @DisplayName("Click on Add to WL movie test")
    public void addToWLMovieTest() {
        var indexCard = neonPage.heroCarousel.findHeroCard("movie");
        String expectedTitle = neonPage.heroCarousel.cards.get(indexCard).title.getText();
        neonPage.heroCarousel.cards.get(indexCard).watchlistButton.click();
        actions.shouldSee(neonPage.watchListCollection);
        actions.shouldHaveTextToBe(neonPage.watchListCollection.titles.get(0), expectedTitle);
    }

    @Test
    @DisplayName("Click on Add to WL TV test")
    public void addToWLTvTest() {
        var indexCard = neonPage.heroCarousel.findHeroCard("TV");
        String expectedTitle = neonPage.heroCarousel.cards.get(indexCard).title.getText();
        neonPage.heroCarousel.cards.get(indexCard).watchlistButton.click();
        actions.shouldSee(neonPage.watchListCollection);
        actions.shouldHaveTextToBe(neonPage.watchListCollection.titles.get(0), expectedTitle);
    }

    @Test
    @DisplayName("Select Tab by index test")
    public void selectTabByIndexTest() {

        heroCarousel.tabs.get(2).click();
        Assertions.assertTrue(heroCarousel.tabs.get(2).isActive(), "Tab is  active");
        Assertions.assertFalse(heroCarousel.tabs.get(0).isActive(), "Tab is not active");
    }

    @Test
    @DisplayName("Tab timeout test")
    public void tabTimeoutTest() {
        Assertions.assertTrue(heroCarousel.tabs.get(0).isActive(), "Tab is  active");
        actions.shouldHaveAttributeContains(heroCarousel.tabs.get(1), "class", "is-active");
        Assertions.assertFalse(heroCarousel.tabs.get(0).isActive(), "Tab is  not active");
    }

    @Test
    @DisplayName("Reverse click on left arrow test")
    public void reverseClickOnLeftArrow() {

        int count = heroCarousel.tabs.size();
        heroCarousel.leftArrow.click();
        Assertions.assertTrue(heroCarousel.tabs.get(count - 1).isActive(), "Tab is  active");
        Assertions.assertFalse(heroCarousel.tabs.get(0).isActive(), "Tab is  not active");

    }

    @Test
    @DisplayName("Reverse click on right arrow test")
    public void reverseClickOnRightArrow() {

        int count = heroCarousel.tabs.size();
        heroCarousel.tabs.get(count - 1).click();
        heroCarousel.rightArrow.click();
        Assertions.assertFalse(heroCarousel.tabs.get(count - 1).isActive(), "Tab is not active");
        Assertions.assertTrue(heroCarousel.tabs.get(0).isActive(), "Tab is active");

    }

    @Test
    @DisplayName("Test Scroll")
    public void testScroll() {
        actions.scrollTo(neonPage.promoCard);
        System.out.println();
    }

}
