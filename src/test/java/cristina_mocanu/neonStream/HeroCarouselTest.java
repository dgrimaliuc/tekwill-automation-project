package cristina_mocanu.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HeroCarouselTest extends NeonStreamBaseTest {

    @Test
    @DisplayName("Smoke Hero Carousel test")
    public void smokeHeroCarouselTest() {
        actions.shouldBeDisplayed(page.heroCarousel);
        var card = page.heroCarousel.cards.getFirst();
        actions.shouldBeDisplayed(card.title);
        actions.shouldBeDisplayed(card.description);
        actions.shouldBeDisplayed(card.image);
        actions.shouldBeDisplayed(card.addWatchList);
        actions.shouldBeDisplayed(card.removeFromWatchList);
    }


    @Test
    @DisplayName("Click on tab 5 test")
    public void clickOnTabTest() {
        var num = 4;
        page.heroCarousel.tabs.get(num).click();
        var card = page.heroCarousel.cards.get(num);
        actions.shouldHaveTextToBe(card.title, "Venom: The Last Dance");
        actions.shouldHaveTextContains(card.description, "Eddie and Venom are on the run.");


    }
    @Test
    @DisplayName("Hero Card Watch Now click test")
    public void heroCardWatchNowClickTest(){
        page.heroCarousel.activeCard.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");

    }

    @Test
    @DisplayName("Hero Card Title click test")
    public void heroCardTitleClickTest(){
        page.heroCarousel.activeCard.title.click();
        actions.waitForCurrentURLContains("/movie/");

    }

}
