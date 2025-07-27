package denis_grimaliuc.neonStream;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NeonStreamContentPage extends BasePage {

    @FindBy(css = "[class*=hero-content_hero-title]")
    public WebElement title;

    public NeonStreamContentPage(WebDriver driver) {
        super(driver);
    }
}
