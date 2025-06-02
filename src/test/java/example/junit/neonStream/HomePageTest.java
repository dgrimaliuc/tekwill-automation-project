package example.junit.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomePageTest extends NeonStreamBaseTest {
    @Test
    @DisplayName("Browse card click test")
    public void browseCardClickTest() {
        var card = homePage.carousel.getFirst().cards.getFirst().title;
        actions.scrollTo(card);
        card.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Carousel right click test")
    public void carouselRightClickTest() {
        var carousel = homePage.carousel.getFirst();
        var carouselTitle = carousel.title;
        actions.scrollTo(carouselTitle);
        carousel.rightArrow.click();
        actions.shouldHaveAttribute(homePage.carousel.getFirst().cards.getFirst(), "inert", "true");
        actions.shouldNotHaveAttribute(homePage.carousel.getFirst().cards.get(12), "inert", "true");

        carousel.leftArrow.click();

        actions.shouldNotHaveAttribute(homePage.carousel.getFirst().cards.getFirst(), "inert", "true");
        actions.shouldHaveAttribute(homePage.carousel.getFirst().cards.get(12), "inert", "true");

    }

    @Test
    @DisplayName("Carousel title click test")
    public void carouselTitleClickTest() {
        homePage.heroCarousel.cards.getFirst().watchListButton.add.click();
        actions.scrollTo(homePage.carousel.getFirst().title);
        homePage.carousel.getFirst().title.click();
        actions.waitForCurrentURLContains("/watchlist");
    }

    @Test
    @DisplayName("Single promo show card test")
    public void singlePromoShowCardTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        actions.shouldBeDisplayed(card.image);
        actions.shouldBeDisplayed(card.title);
        actions.shouldBeDisplayed(card.description);
        actions.shouldBeDisplayed(card.addToWatchlist);
        actions.shouldBeDisplayed(card.watchNow);
    }

    @Test
    @DisplayName("Single promo show card title click test")
    public void singlePromoShowCardTitleClickTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        card.title.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Single promo show card image click test")
    public void singlePromoShowCardImageClickTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        card.image.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Single promo show card image click test")
    public void singlePromoShowCardWatchNowClickTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        card.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
    }
}
