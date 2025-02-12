package Lilia_Rosca.LR_JUnit.NeonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LR_HomePageTests extends LR_NeonStreamBaseTest{
    // 07.02
    @Test
    @DisplayName("Browse card click test")
    public void browseCardClickTest() {
        var card = homePage.carousel.getFirst().browseCards.getFirst().title;
        actions.scrollTo(card, 100);
        card.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Carousel right and left click test")
    public void carouselRightAndLeftClickTest() {
        var carousel = homePage.carousel.getFirst();
        var carouselTitle = carousel.title;
        actions.scrollTo(carouselTitle, 100);
        carousel.rightArrow.click();
        // waitFor(2);
        actions.shouldHaveAttribute(homePage.carousel.getFirst().browseCards.getFirst(), "inert", "true");
        actions.shouldNotHaveAttribute(homePage.carousel.getFirst().browseCards.get(10), "inert", "true");

        carousel.leftArrow.click();
        actions.shouldNotHaveAttribute(homePage.carousel.getFirst().browseCards.getFirst(), "inert", "true");
        actions.shouldHaveAttribute(homePage.carousel.getFirst().browseCards.get(10), "inert", "true");
    }

    @Test
    @DisplayName("Carousel title click test")
    public void carouselTitleClickTest() {
        homePage.heroCarousel.cards.getFirst().watchlistButton.add.click();
        actions.scrollTo(homePage.carousel.getFirst().title, 100);
        homePage.carousel.getFirst().title.click();
        actions.waitForCurrentURLContains("/watchlist");
    }

    @Test
    @DisplayName("Single promo show card test")
    public void singlePromoShowCardTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        actions.shouldBeDisplayed(card.title);
        actions.shouldBeDisplayed(card.image);
        actions.shouldBeDisplayed(card.description);
        actions.shouldBeDisplayed(card.watchlistButton.add);
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
    @DisplayName("Single promo show card watchNow button click test")
    public void singlePromoShowCardWatchNowButtonClickTest() {
        actions.scrollTo(homePage.singlePromoCards);
        var card = homePage.singlePromoCards.getFirst();
        card.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
    }
}