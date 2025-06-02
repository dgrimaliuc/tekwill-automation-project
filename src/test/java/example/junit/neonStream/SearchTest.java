package example.junit.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchTest extends NeonStreamBaseTest {

    @Test
    @DisplayName("Click on search input test")
    public void clickOnSearchInputTest() {
        header.searchInput.click();
        actions.shouldSee(searchPage.starsImage);
        actions.shouldSee(searchPage.title);
        actions.shouldSee(searchPage.description);
        actions.shouldHaveTextToBe(searchPage.title, "Ready to dive in?");
        actions.shouldHaveTextToBe(searchPage.description, "Start your search to explore a world of amazing content.");
    }

    @Test
    @DisplayName("Search for something test")
    public void searchForSomethingTest() {
        header.searchInput.click();
        header.searchInput.sendKeys("The");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        for (var card : searchPage.cards.subList(0, 5)) {
            actions.shouldHaveTextContains(card.title, "the", true);
        }
        var currentCardsSize = searchPage.cards.size();
        actions.scrollTo(footer.container);
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, currentCardsSize);
    }

    @Test
    @DisplayName("Click on result card test")
    public void clickOnResultCardTest() {
        header.searchInput.click();
        header.searchInput.sendKeys("The");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        searchPage.cards.getFirst().click();
        actions.shouldBeDisplayed(moviePage.contentHeader);
    }

    @Test
    @DisplayName("Empty search results test")
    public void emptySearchResultsTest() {
        header.searchInput.click();
        header.searchInput.sendKeys("aaaaaaaaaaa");
        actions.shouldSee(searchPage.starsImage);
        actions.shouldSee(searchPage.title);
        actions.shouldSee(searchPage.description);
        actions.shouldHaveTextToBe(searchPage.title, "No results found");
        actions.shouldHaveTextToBe(searchPage.description, "Sorry, we couldn't find any results for your search. Try again with a different query.");
    }

    @Test
    @DisplayName("Direct navigation to search page with query test")
    public void directNavigationTest() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/search?q=the");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        actions.shouldHaveAttribute(header.searchInput, "value", "the");

    }
}
