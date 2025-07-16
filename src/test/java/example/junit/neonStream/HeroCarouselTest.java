package example.junit.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HeroCarouselTest extends NeonStreamBaseTest {

    @Test
    @DisplayName("Smoke Hero Carousel Test")
    public void smokeHeroCarouselTest() {
        actions.shouldBeDisplayed(homePage.heroCarousel.activeCard);
        var card = homePage.heroCarousel.cards.getFirst();
        homePage.heroCarousel.tabs.getFirst().click();

        actions.shouldSee(card.title);
        actions.shouldSee(card.image);
        actions.shouldSee(card.description);
        actions.shouldSee(card.watchNow);
        actions.shouldSee(card.watchListButton.add);
    }

    @Test
    @DisplayName("Hero Carousel animation timing test")
    public void animationTimingTest() {
        var classAttributeT1 = homePage.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttributeT1, equalTo(true));

        waitFor(10);

        var classAttributeT2 = homePage.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttributeT2, equalTo(false));

        var secondTabClassAttribute = homePage.heroCarousel.tabs.get(1).isActive();
        assertThat(secondTabClassAttribute, equalTo(true));
    }

    @Test
    @DisplayName("Click on tab test")
    public void clickOnTabTest() {
        var num = 4;
        homePage.heroCarousel.tabs.get(num).click();
        var card = homePage.heroCarousel.cards.get(num);
        actions.shouldHaveTextToBe(card.title, "Venom: The Last Dance");
        actions.shouldHaveTextContains(card.description, "Eddie and Venom are on the run.");
    }

    @Test
    @DisplayName("Arrows test")
    public void arrowsTest() {
        actions.leftClick(homePage.heroCarousel.leftArrow);
        actions.shouldHaveTextToBe(homePage.heroCarousel.activeCard.title, "The Fall Guy");
        actions.leftClick(homePage.heroCarousel.rightArrow);
        actions.shouldHaveTextToBe(homePage.heroCarousel.activeCard.title, "The Gorge");
    }

    @Test
    @DisplayName("Hero card Watch now click test")
    public void heroCardWatchNowClickTest() {
        homePage.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Hero card Title click test")
    public void heroCardTitleClickTest() {
        homePage.heroCarousel.activeCard.title.click();
        actions.waitForCurrentURLContains("/movie/");
    }
}
