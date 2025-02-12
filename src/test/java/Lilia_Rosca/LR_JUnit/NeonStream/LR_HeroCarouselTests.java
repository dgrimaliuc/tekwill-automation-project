package Lilia_Rosca.LR_JUnit.NeonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class LR_HeroCarouselTests extends LR_NeonStreamBaseTest{
    // 05.02
    @Test
    @DisplayName("Smoke Hero Carousel Test")
    public void smokeHeroCarouselTest() {
        actions.shouldBeDisplayed(homePage.heroCarousel);
        var card = homePage.heroCarousel.cards.getFirst();
        actions.shouldBeDisplayed(card.title);
        actions.shouldBeDisplayed(card.image);
        actions.shouldBeDisplayed(card.description);
        actions.shouldBeDisplayed(card.watchNow);
        actions.shouldBeDisplayed(card.watchlistButton.add);
    }

    @Test
    @DisplayName("HC animation timing test")
    public void animationTimingTest() {
        var classAttribute1 = homePage.heroCarousel.tabs.getFirst().getAttribute("class");
        assertThat(classAttribute1, containsString("is-active"));

        waitFor(10);
        var classAttribute2 = homePage.heroCarousel.tabs.getFirst().getAttribute("class");
        assertThat(classAttribute2, not(containsString("is-active")));
        var secondTabClassAttribute = homePage.heroCarousel.tabs.get(1).getAttribute("class");
        assertThat(secondTabClassAttribute, containsString("is-active"));
    }

    @Test
    @DisplayName("HC animation timing test var 2")
    public void animationTimingTest2() {
        var classAttribute1_2 = homePage.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttribute1_2, equalTo(true));

        waitFor(10);
        var classAttribute2 = homePage.heroCarousel.tabs.getFirst().isActive();
        assertThat(classAttribute2, equalTo(false));
        var secondTabClassAttribute = homePage.heroCarousel.tabs.get(1).isActive();
        assertThat(secondTabClassAttribute, equalTo(true));
    }

    @Test
    @DisplayName("Click on a tab test")
    public void clickOnTabTest() {
        var num = 4;
        homePage.heroCarousel.tabs.get(num).click();
        var card = homePage.heroCarousel.cards.get(num);
        actions.shouldHaveTextToBe(card.title, "Venom: The Last Dance");
        actions.shouldHaveTextContains(card.description, "Hunted by both of their worlds");

    }

    @Test
    @DisplayName("Arrow test")
    public void arrowTest() {
        actions.leftClick(homePage.heroCarousel.leftArrow);
        actions.shouldHaveTextToBe(homePage.heroCarousel.activeCard.title, "The Fall Guy");
        actions.leftClick(homePage.heroCarousel.rightArrow);
        actions.shouldHaveTextToBe(homePage.heroCarousel.activeCard.title, "Borderlands");
    }
    // hero card Watch now click test
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
