package Lilia_Rosca.poms.LR_NeonStream;

import Lilia_Rosca.components.LR_NeonStream.LR_BrowseCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_SearchPage extends BasePage {
// 12.02
    @FindBy(css = "[class*=search_stars]")
    public WebElement starsImage;

    @FindBy(css = "[class*=search_empty-state] h2")
    public WebElement title;

    @FindBy(css = "[class*=search_description]")
    public WebElement description;

    @FindBy (css = "[class*=grid-content_content-wrapper]")
    public Components<LR_BrowseCard>cards;

    public LR_SearchPage(WebDriver driver) {super(driver);}
}
