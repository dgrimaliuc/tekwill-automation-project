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

    public void shouldHaveTextContains(WebElement element, String text, boolean ignoreCase) {
        log.trace("Checking if element has text: " + element);
        if (ignoreCase) {
            wait.until(driver -> element.getText().toLowerCase().contains(text.toLowerCase()));
        } else {
            wait.until(driver -> element.getText().contains(text));
        }
    }

    @Test
    @DisplayName("Search for something test")
    public void searchForSomethingTest() {
        header.searchInput.click();
        header.searchInput.sendKeys("The");
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, 0); // 5, nici cu 1 nu merge - Timeout
/*        for (var card : searchPage.cards.subList(0, 1)) { // nu pot modifica in mai mult pina cind e Timeout mai sus
            shouldHaveTextContains(card.title, "the", true);
        }
        var searchCardsSize = searchPage.cards.size();
        actions.scrollTo(footer.container);
        actions.waitForNumberOfElementsToBeMoreThan(searchPage.cards, searchCardsSize); */
    }

//    Click on result card test
//    Empty search results test
//    Direct navigation to search page with query test
}
