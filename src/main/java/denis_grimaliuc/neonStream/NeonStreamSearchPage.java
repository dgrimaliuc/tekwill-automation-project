package denis_grimaliuc.neonStream;

import example.actions.BaseActions;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NeonStreamSearchPage extends BasePage {

    @FindBy(css = "[class*=grid-content_content-wrapper]>div")
    public Components<BrowseCard> cards;

    @FindBy(css = "[class*=search_title]")
    public WebElement title;
    @FindBy(css = "[class*=search_container] input")
    public WebElement searchInput;
    @FindBy(css = "[class*=empty-state-wrapper]")
    public NoResultsSearchSection noResultsSection;

    public NeonStreamSearchPage(WebDriver driver) {
        super(driver);
    }

    public void shouldSeeResultsFor(String text) {
        BaseActions actions = new BaseActions(driver);

        actions.waitForNumberOfElementsToBeMoreThan(cards, 1);

        for (BrowseCard card : cards.subList(0, 4)) {
            actions.shouldHaveTextContains(card.title, text, true);
        }
    }
}
