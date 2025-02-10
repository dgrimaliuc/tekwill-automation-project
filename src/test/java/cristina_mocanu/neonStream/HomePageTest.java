package cristina_mocanu.neonStream;

import internal.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;

public class HomePageTest extends NeonStreamBaseTest{
@Test
    @DisplayName("Browse card click test")
    public void browseCardClickTest(){
    var card = page.carousel.getFirst().cards.getFirst().title;
    actions.scrollTo(card);
    waitFor(2);
    card.click();
    actions.waitForCurrentURLContains("/movie");
    waitFor(2);

}

    @Test
    @DisplayName("Carousel right click test")
    public void carouselRightClickTest() {
        var carousel = page.carousel.getFirst();
        var carouselTitle = carousel.title;
        actions.scrollTo(carouselTitle, 500);
        carousel.rightArrow.click();
        actions.shouldHaveAttribute(page.carousel.getFirst().cards.getFirst(), "inert", "true");
        actions.shouldNotHaveAttribute(page.carousel.getFirst().cards.getFirst(), "inert", "true");


        carousel.leftArrow.click();
        actions.shouldNotHaveAttribute(page.carousel.getFirst().cards.getFirst(), "inert", "true");
        actions.shouldHaveAttribute(page.carousel.getFirst().cards.getFirst(), "inert", "true");


    }
    @Test
    @DisplayName("Carousel title click test")
    public void carouselTitleClickTest() {
    page.heroCarousel.cards.getFirst().addToWatchList.click();
    actions.scrollTo(page.carousel.getFirst().title);
    page.carousel.getFirst().title.click();
    actions.waitForCurrentURLContains("/watchlist");


    }}
