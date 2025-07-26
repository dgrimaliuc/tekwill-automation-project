package Magda_Petrachi.NeoStreamJUnit;

import Magda_Petrachi.NeonStream.NeoStreamPage;
import example.components.neonStream.Tab;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;

public class NeoStreamTests extends BaseTest {

    NeoStreamPage page = new NeoStreamPage(driver);

    @BeforeEach
    void setup() {
        log.info("Open Neo Stream page");
        driver.get("https://neon-stream.web.app/");
        actions.waitForNumberOfElements(page.placeholders, 0);
    }

    @Test
    public void smokeTestHeroCarusel() {
        actions.shouldSee(page.heroCarousel.cards.getFirst().title);
    }

    @Test
    public void clickOnHeroCardTitle() {
        page.heroCarousel.activeCard.title.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    public void waitForActiveCardToChangeTest() {
        String fTabClass = page.heroCarousel.tabs.getFirst().getAttribute("class");
        assertThat(fTabClass, containsString("is-active"));
        String fImage = page.heroCarousel.activeCard.image.getAttribute("src");

        waitFor(10);

        String sTabClass = page.heroCarousel.tabs.get(1).getAttribute("class");
        assertThat(sTabClass, containsString("is-active"));

        String sImage = page.heroCarousel.activeCard.image.getAttribute("scr");
        assertThat(fImage, not(equals(sImage)));
    }

//    @Test
//    public void watchMovieHeroCardClickTVTest() {
//        page.heroCarousel.activeCard.watchNow.click();
//        actions.waitForCurrentURLContains("/tv/");
//    }


//    @Test
//    public void watchNowHeroCardClickTvTest() {
//        page.heroCarousel.findContent("tv");
//        page.heroCarousel.activeCard.watchNow.click();
//        actions.waitForCurrentURLContains("*/tv/\\d+/watch/.*");
//    }

    @Test
    public void leftArrowsClickTest() {
        page.heroCarousel.leftArrow.click();
        Tab lastTab = page.heroCarousel.tabs.getLast();
        assertThat(String.valueOf(lastTab.isActive()), equalTo(true));
    }

    @Test
    public void tabClickTest() {
        var index = 3;
        page.heroCarousel.tabs.get(index).click();
        String expImage = page.heroCarousel.cards.get(index).image.getAttribute("scr");
        String activeImg = page.heroCarousel.activeCard.image.getAttribute("scr");
        assertThat(activeImg, equalTo(expImage));
        assertThat(page.heroCarousel.tabs.get(index).isActive(), equalTo(true));

    }

//    @Test
//    public void addToWatchListHeroCardTest() {
//        HeroCard heroCard = page.heroCarousel.cards.getFirst();
//        heroCard.watchListButton.click();
//        actions.shouldSee(heroCard.removeListButton);
//        actions.shouldNotBeDisplayed(heroCard.watchListButton);
//
//        actions.waitForNumberOfElements(page.watchListCollection.cards, 1);
//        String fWhachListTitle = page.watchListCollection.cards.getFirst().title.getText();
//
//        assertThat(fWhachListTitle, containsString(heroCard.title.getText()));
//    }


}
