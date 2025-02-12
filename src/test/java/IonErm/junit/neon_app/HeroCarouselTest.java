package IonErm.junit.neon_app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HeroCarouselTest extends NeonAppBaseTest {

    @Test
    @DisplayName("Smoke Hero Carousel Test")
    public void smokeHeroTest() {
        actions.shouldBeDisplayed(homePage.heroCarousel);
        var card = homePage.heroCarousel.heroCards.getFirst();
        actions.shouldBeDisplayed(card.title);
        actions.shouldBeDisplayed(card.image);
        actions.shouldBeDisplayed(card.description);
        actions.shouldBeDisplayed(card.watchNow);
        actions.shouldBeDisplayed(card.watchListButton.add);
    }

    @Test
    @DisplayName("Animation Timeout Test")
    public void animationTimeoutTest() {
        var classAttributeTry1 = homePage.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttributeTry1, equalTo(true));
        waitFor(10);
        var classAttributeTry2 = homePage.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttributeTry2, equalTo(false));
        var secondClassAttribute = homePage.heroCarousel.tabs.get(1).isActive();
        assertThat(secondClassAttribute, equalTo(true));
    }

    @Test
    @DisplayName("Click on tab test")
    public void clickOnTab() {
        var num = 6;
        homePage.heroCarousel.tabs.get(num).click();
        var card = homePage.heroCarousel.heroCards.get(num);
        actions.shouldHaveTextToBe(card.title, "Kraven the Hunter");
        actions.shouldHaveTextContains(card.description, "Kraven Kravinoff's complex relationship with his ruthless gangster father, Nikolai, starts him down a path of vengeance");
        waitFor(3);
    }

    @Test
    @DisplayName("Arrows test")
    public void arrowsTest() {
        actions.leftClick(homePage.heroCarousel.arrowLeft);
        actions.shouldHaveTextToBe(homePage.heroCarousel.activeCard.title, "The Fall Guy");
        actions.leftClick(homePage.heroCarousel.arrowRight);
        actions.shouldHaveTextToBe(homePage.heroCarousel.activeCard.title, "Borderlands");
    }

    @Test
    @DisplayName("Hero Card Watch Now Click Test")
    public void heroCardWatchNowClickTest() {
        homePage.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
        waitFor(2);
//        var containsMovie = driver.getCurrentUrl().contains("/movie/");
//        assertThat(containsMovie, equalTo(true));
    }

    @Test
    @DisplayName("Hero Card Title Click Test")
    public void heroCardTitleClickTest() {
        homePage.heroCarousel.activeCard.title.click();
        actions.waitForCurrentURLContains("/movie/");
        waitFor(2);
    }
}
