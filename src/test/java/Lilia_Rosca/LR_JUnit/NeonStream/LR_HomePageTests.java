package Lilia_Rosca.LR_JUnit.NeonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static example.actions.BaseActions.*;

public class LR_HomePageTests extends LR_NeonStreamBaseTest{
// 07.02
    public void scrollTo(WebElement element) { // din BaseActions, de sters dupa git pull
        scrollTo(element, 1000);
    }
    public void scrollTo(WebElement element, int top) { // din BaseActions, de sters dupa git pull
        setTimeouts(driver, 1);
        while (!actions.isDisplayed(element)) {
            actions.executeScript(String.format("""
                    window.scrollBy({top: %d, left: 0,});
                    """, top), null);
        }
        // works without
        // actions.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block:\"center\"});", element);
        setDefaultTimeouts(driver);
    }

    public void scrollTo(Components<?> elements) { // din BaseActions, de sters dupa git pull
        setTimeouts(driver, 1);
        while (elements.isEmpty()) {
            actions.executeScript(String.format("""
                    window.scrollBy({top: %d, left: 0,});
                    """, 1000), null);
        }
        actions.shouldBeDisplayed(elements.getFirst());
//        scrollIntoCenter(elements.getFirst().getParent());
        setDefaultTimeouts(driver);
    }

    public void scrollIntoCenter(WebElement element) {
        actions.executeScript("arguments[0].scrollIntoView({block:\"center\", inline: \"nearest\"});", element);
    }

    @Test
    @DisplayName("Browse card click test")
    public void browseCardClickTest() {
        var card = page.carousel.getFirst().browseCards.getFirst().title;
        scrollTo(card, 100); // BaseActions actions.scrollTo(card);
        card.click();
        actions.waitForCurrentURLContains("/movie/");
    }

    @Test
    @DisplayName("Carousel right and left click test")
    public void carouselRightAndLeftClickTest() {
        var carousel = page.carousel.getFirst();
        var carouselTitle = carousel.title;
        scrollTo(carouselTitle, 100); // BaseActions actions.scrollTo(carouselTitle, 200);
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
        scrollTo(page.carousel.getFirst().title, 100);
        page.carousel.getFirst().title.click();
        actions.waitForCurrentURLContains("/watchlist");
    }

    @Test
    @DisplayName("Single promo show card test")
    public void singlePromoShowCardTest() {
        scrollTo(page.singlePromoCards);

    }
    // Single promo show card title click test
    // Single promo show card image click test
    // Single promo show card watchNow button click test
}
