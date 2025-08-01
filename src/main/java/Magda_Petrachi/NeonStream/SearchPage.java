package Magda_Petrachi.NeonStream;

import example.actions.BaseActions;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {

//    @FindBy(css = "[class*=rid-content_content-wrapper]>div")
//    public Collections<BrowseCard> cards;


    @FindBy(css = "[class*=search_title]")
    public WebElement title;

    @FindBy(css = "[class*=search_container] input")
    public WebElement searchInput;

    @FindBy(css = "[class*=search_container]")
    public NoResultsSearchSection emptySearchSection;

    @FindBy(css = "[class*=empty-state-wrapper]")
    public NoResultsSearchSection noResultsSearch;

    @FindBy(css = ".browse-card_browse-card-hover__9SXrH")
    public List<WebElement> cards;


    public SearchPage(WebDriver driver) {
        super(driver);
    }


    public void shoudSeeResultsFor(String query) {
        BaseActions actions = new BaseActions(driver);
        actions.waitForNumberOfElementsToBeMoreThan(cards, 1);
//        for (BrowseCard card : cards.subList(0, 4)) {
//            actions.shouldHaveTextContains(card.title, text, true);

//        }
    }
}