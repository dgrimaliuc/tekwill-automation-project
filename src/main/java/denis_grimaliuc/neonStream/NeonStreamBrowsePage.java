package denis_grimaliuc.neonStream;

import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NeonStreamBrowsePage extends BasePage {

    @FindBy(css = "[class*=grid-content_content-wrapper]>div")
    public Components<BrowseCard> cards;

    @FindBy(css = ".browse-title")
    public WebElement title;
    
    @FindBy(css = "[class*=styles_browse_actions]")
    public WebElement browseActions;

    public NeonStreamBrowsePage(WebDriver driver) {
        super(driver);
    }
}
