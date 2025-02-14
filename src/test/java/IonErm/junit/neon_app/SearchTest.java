package IonErm.junit.neon_app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SearchTest extends NeonAppBaseTest {

    @Test
    @DisplayName("Click on search input test")
    public void clickOnSearchInputTest() {
        header.search.click();
        actions.shouldSee(searchPage.starsImg);
        actions.shouldSee(searchPage.title);
        actions.shouldSee(searchPage.description);
        actions.shouldHaveTextToBe(searchPage.title, "Ready to dive in?");
        actions.shouldHaveTextToBe(searchPage.description, "Start your search to explore a world of amazing content.");
    }

    @Test
    @DisplayName("Search something test")
    public void searchSomethingTest() {
        header.search.click();
        header.search.sendKeys("bleach");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        for (var card : searchPage.cards.subList(0, 5)) {
            actions.shouldHaveTextContains(card.title, "Bleach");
        }
        var currentSize = searchPage.cards.size();
        actions.scrollTo(footer.container);
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, currentSize);
    }

    @Test
    @DisplayName("Click on result card test")
    public void clickOnResultCardTest() {
        header.search.click();
        header.search.sendKeys("bleach");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        searchPage.cards.getFirst().click();
        actions.shouldBeDisplayed(moviePage.contentHeader);
    }

    @Test
    @DisplayName("Empty search result test")
    public void emptySearchResultTest() {
        header.search.click();
        header.search.sendKeys(";kwdljqwhbc");
        actions.shouldSee(searchPage.starsImg);
        actions.shouldSee(searchPage.title);
        actions.shouldSee(searchPage.description);
        actions.shouldHaveTextToBe(searchPage.title, "No results found");
        actions.shouldHaveTextToBe(searchPage.description, "Sorry, we couldn't find any results for your search. Try again with a different query.");
    }

    @Test
    @DisplayName("Direct navigation to search page with query test")
    public void directSearchTest() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/search?q=bleach");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        actions.shouldHaveAttribute(header.search, "value", "bleach");
    }
}
