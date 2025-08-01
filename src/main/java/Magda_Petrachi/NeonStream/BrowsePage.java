package Magda_Petrachi.NeonStream;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowsePage extends BasePage {

//    @FindBy(css = "[calss*=rid-content_contect-wrapper]>div")
//    public Collections<BrowseCard> cards;

    @FindBy(css = ".browse-title")
    public WebElement title;

    @FindBy(css = "[class*=styles_browse_actions]")
    public WebElement browseActions;

    @FindBy(css = ".browse-card_browse-card-body__Lp3BP undefined")
    public BrowseCard browseCard;


    public BrowsePage(WebDriver driver) {
        super(driver);
    }
}