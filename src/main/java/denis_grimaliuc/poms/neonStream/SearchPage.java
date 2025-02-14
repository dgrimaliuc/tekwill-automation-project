package denis_grimaliuc.poms.neonStream;

import denis_grimaliuc.components.neonStream.BrowseCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
    @FindBy(css = "[class*=search_stars]")
    public WebElement starsImage;

    @FindBy(css = "[class*=search_empty-state] h2")
    public WebElement title;

    @FindBy(css = "[class*=search_description]")
    public WebElement description;

    @FindBy(css = "[class*=grid-content_content-wrapper] > div")
    public Components<BrowseCard> cards;


    public SearchPage(WebDriver driver) {
        super(driver);
    }
}
