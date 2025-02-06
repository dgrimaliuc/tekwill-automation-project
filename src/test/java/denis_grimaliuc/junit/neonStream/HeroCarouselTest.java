package denis_grimaliuc.junit.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HeroCarouselTest extends NeonStreamBaseTest {

    @Test
    @DisplayName("Smoke Hero Carousel Test")
    public void smokeHeroCarouselTest() {
        actions.shouldBeDisplayed(page.heroCarousel);
        var card = page.heroCarousel.cards.getFirst();

        actions.shouldBeDisplayed(card.title);
        actions.shouldBeDisplayed(card.image);
        actions.shouldBeDisplayed(card.description);
        actions.shouldBeDisplayed(card.watchNow);
        actions.shouldBeDisplayed(card.addToWathList);
    }

    @Test
    @DisplayName("Hero Carousel animation timing test")
    public void animationTimingTest() {
        var classAttributeT1 = page.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttributeT1, equalTo(true));

        waitFor(10);

        var classAttributeT2 = page.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttributeT2, equalTo(false));

        var secondTabClassAttribute = page.heroCarousel.tabs.get(1).isActive();
        assertThat(secondTabClassAttribute, equalTo(true));
    }

    @Test
    @DisplayName("Click on tab test")
    public void clickOnTabTest() {
        var num = 4;
        page.heroCarousel.tabs.get(num).click();
        var card = page.heroCarousel.cards.get(num);
        actions.shouldHaveTextToBe(card.title, "Venom: The Last Dance");
        actions.shouldHaveTextContains(card.description, "Eddie and Venom are on the run.");

    }


    @Test
    @DisplayName("Arrows test")
    public void arrowsTest() {
        actions.leftClick(page.heroCarousel.leftArrow);
        actions.shouldHaveTextToBe(page.heroCarousel.activeCard.title, "The Fall Guy");
        actions.leftClick(page.heroCarousel.rightArrow);
        actions.shouldHaveTextToBe(page.heroCarousel.activeCard.title, "Borderlands");
    }


    @Test
    @DisplayName("Hero card Watch now click test")
    public void heroCardWatchNowClickTest() {
        page.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Hero card Title click test")
    public void heroCardTitleClickTest() {
        page.heroCarousel.activeCard.title.click();
        actions.waitForCurrentURLContains("/movie/");
    }
}
