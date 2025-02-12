package IonErm.junit.neon_app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;

public class HomePageTests extends NeonAppBaseTest {
    @Test
    @DisplayName("Browse card click test")
    public void broseCardClickTest() {
        var card = homePage.carousel.getFirst().cards.getFirst().title;
        actions.scrollTo(card);
        waitFor(2);
        card.click();
        actions.waitForCurrentURLContains("/movie/");
        waitFor(2);
    }

    @Test
    @DisplayName("Carousel right click test")
    public void carouselRightClickTest() {
        var carousel = homePage.carousel.getFirst();
        var carouselTitle = carousel.title;
        actions.scrollTo(carouselTitle);
        carousel.arrowRight.click();
        actions.shouldHaveAttribute(homePage.carousel.getFirst().cards.getFirst(), "inert", "true");
        actions.shouldNotHaveAttribute(homePage.carousel.getFirst().cards.get(14), "inert", "false");
        waitFor(2);
        carousel.arrowLeft.click();
        actions.shouldNotHaveAttribute(homePage.carousel.getFirst().cards.getFirst(), "inert", "false");
        actions.shouldHaveAttribute(homePage.carousel.getFirst().cards.get(14), "inert", "true");
        waitFor(2);
    }

    @Test
    @DisplayName("Carousel title click test")
    public void carouselTitleClickTest() {
        homePage.heroCarousel.heroCards.getFirst().watchListButton.add.click();
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
        actions.shouldBeDisplayed(card.watchNow);
        actions.shouldBeDisplayed(card.addToWatchlist);
    }

    @Test
    @DisplayName("Single promo card title click test")
    public void singlePromoCardTitleClickTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        card.title.click();
        actions.waitForCurrentURLContains("/movie/");
        waitFor(3);
    }

    @Test
    @DisplayName("Single promo card image click test")
    public void singlePromoCardImageClickTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        card.image.click();
        actions.waitForCurrentURLContains("/movie/");
        waitFor(3);
    }

    @Test
    @DisplayName("Single promo card Watch Now click test")
    public void singlePromoCardWatchNowBtnClickTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        card.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
        waitFor(3);
    }
}
