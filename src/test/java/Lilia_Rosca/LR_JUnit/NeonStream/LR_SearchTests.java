package Lilia_Rosca.LR_JUnit.NeonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static example.actions.BaseActions.waitFor;

public class LR_SearchTests extends LR_NeonStreamBaseTest{
    // 12.02
    @Test
    @DisplayName("Click on search input test")
    public void clickOnSearchInputTest() {
        header.searchInput.click();
        actions.shouldSee(searchPage.starsImage);
        actions.shouldSee(searchPage.title);
        actions.shouldHaveTextToBe(searchPage.title, "Ready to dive in?");
        actions.shouldSee(searchPage.description);
        actions.shouldHaveTextToBe(searchPage.description, "Start your search to explore a world of amazing content.");
    }

    @Test
    @DisplayName("Search for something test")
    public void searchForSomethingTest() {
        header.searchInput.click();
        header.searchInput.sendKeys("the");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        for (var card : searchPage.cards.subList(0, 5)) {
            actions.shouldHaveTextContains(card.title, "the", true);
        }
        var searchCardsSize = searchPage.cards.size();
        actions.scrollTo(footer.container);
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, searchCardsSize);
    }
    //    Click on result card test
    @Test
    @DisplayName("Click on result card test")
    public void clickOnResultCardTest() {
        header.searchInput.click();
        header.searchInput.sendKeys("The");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        searchPage.cards.getFirst().click();
        actions.shouldBeDisplayed(moviePage.contentHeader);// same error as in previous test
    }

    @Test
    @DisplayName("Empty search results test")
    public void emptySearchResultsTest() {
        header.searchInput.click();
        header.searchInput.sendKeys("122345678");
        actions.shouldSee(searchPage.title);
        actions.shouldHaveTextToBe(searchPage.title, "No results found");
        actions.shouldSee(searchPage.description);
        actions.shouldHaveTextToBe(searchPage.description, "Sorry, we couldn't find any results for your search. Try again with a different query.");
    }

    @Test
    @DisplayName("Direct navigation to search page with query test")
    public void directNavigationSearchTest() {
        driver.get("https://neon-stream.web.app/search?q=er");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 5);
        actions.shouldHaveAttribute(header.searchInput, "value", "er");
    }
}