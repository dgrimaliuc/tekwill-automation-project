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
        actions.shouldBeDisplayed(page.heroCarousel);
        var card = page.heroCarousel.heroCards.getFirst();
        actions.shouldBeDisplayed(card.title);
        actions.shouldBeDisplayed(card.image);
        actions.shouldBeDisplayed(card.description);
        actions.shouldBeDisplayed(card.watchNow);
        actions.shouldBeDisplayed(card.addToWatchlist);
    }

    @Test
    @DisplayName("Animation Timeout Test")
    public void animationTimeoutTest() {
        var classAttributeTry1 = page.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttributeTry1, equalTo(true));
        waitFor(10);
        var classAttributeTry2 = page.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttributeTry2, equalTo(false));
        var secondClassAttribute = page.heroCarousel.tabs.get(1).isActive();
        assertThat(secondClassAttribute, equalTo(true));
    }

    @Test
    @DisplayName("Click on tab test")
    public void clickOnTab() {
        var num = 6;
        page.heroCarousel.tabs.get(num).click();
        var card = page.heroCarousel.heroCards.get(num);
        actions.shouldHaveTextToBe(card.title, "Kraven the Hunter");
        actions.shouldHaveTextContains(card.description, "Kraven Kravinoff's complex relationship with his ruthless gangster father, Nikolai, starts him down a path of vengeance");
        waitFor(3);
    }

    @Test
    @DisplayName("Arrows test")
    public void arrowsTest() {
        actions.leftClick(page.heroCarousel.arrowLeft);
        actions.shouldHaveTextToBe(page.heroCarousel.activeCard.title, "The Fall Guy");
        actions.leftClick(page.heroCarousel.arrowRight);
        actions.shouldHaveTextToBe(page.heroCarousel.activeCard.title, "Borderlands");
    }

    @Test
    @DisplayName("Hero Card Watch Now Click Test")
    public void heroCardWatchNowClickTest() {
        page.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
        waitFor(2);
//        var containsMovie = driver.getCurrentUrl().contains("/movie/");
//        assertThat(containsMovie, equalTo(true));
    }

    @Test
    @DisplayName("Hero Card Title Click Test")
    public void heroCardTitleClickTest() {
        page.heroCarousel.activeCard.title.click();
        actions.waitForCurrentURLContains("/movie/");
        waitFor(2);
    }
}
