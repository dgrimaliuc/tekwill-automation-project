package Lilia_Rosca.LR_JUnit.NeonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static example.actions.BaseActions.*;

public class LR_HomePageTests extends LR_NeonStreamBaseTest{
    // 07.02
    @Test
    @DisplayName("Browse card click test")
    public void browseCardClickTest() {
        var card = page.carousel.getFirst().browseCards.getFirst().title;
        actions.scrollTo(card, 100);
        card.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Carousel right and left click test")
    public void carouselRightAndLeftClickTest() {
        var carousel = page.carousel.getFirst();
        var carouselTitle = carousel.title;
        actions.scrollTo(carouselTitle, 100);
        carousel.rightArrow.click();
        // waitFor(2);
        actions.shouldHaveAttribute(page.carousel.getFirst().browseCards.getFirst(), "inert", "true");
        actions.shouldNotHaveAttribute(page.carousel.getFirst().browseCards.get(10), "inert", "true");

        carousel.leftArrow.click();
        actions.shouldNotHaveAttribute(page.carousel.getFirst().browseCards.getFirst(), "inert", "true");
        actions.shouldHaveAttribute(page.carousel.getFirst().browseCards.get(10), "inert", "true");
    }

    @Test
    @DisplayName("Carousel title click test")
    public void carouselTitleClickTest() {
        page.heroCarousel.cards.getFirst().addToWatchList.click();
        actions.scrollTo(page.carousel.getFirst().title, 100);
        page.carousel.getFirst().title.click();
        actions.waitForCurrentURLContains("/watchlist");
    }

    @Test
    @DisplayName("Single promo show card test")
    public void singlePromoShowCardTest() {
        actions.scrollTo(page.singlePromoCards);
        var card = page.singlePromoCards.getFirst();
        actions.shouldBeDisplayed(card.title);
        actions.shouldBeDisplayed(card.image);
        actions.shouldBeDisplayed(card.description);
        actions.shouldBeDisplayed(card.addToWatchList);
        actions.shouldBeDisplayed(card.watchNow);
    }

    @Test
    @DisplayName("Single promo show card title click test")
    public void singlePromoShowCardTitleClickTest() {
        actions.scrollTo(page.singlePromoCards);
        var card = page.singlePromoCards.getFirst();
        card.title.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Single promo show card image click test")
    public void singlePromoShowCardImageClickTest() {
        actions.scrollTo(page.singlePromoCards);
        var card = page.singlePromoCards.getFirst();
        card.image.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Single promo show card watchNow button click test")
    public void singlePromoShowCardWatchNowButtonClickTest() {
        actions.scrollTo(page.singlePromoCards);
        var card = page.singlePromoCards.getFirst();
        card.watchNow.click();
        actions.waitForCurrentURLContains("/movie/");
    }
}
