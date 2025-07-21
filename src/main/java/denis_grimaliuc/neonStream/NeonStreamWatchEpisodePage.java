package denis_grimaliuc.neonStream;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NeonStreamWatchEpisodePage extends BasePage {

    @FindBy(css = ".content-title")
    public WebElement title;

    public NeonStreamWatchEpisodePage(WebDriver driver) {
        super(driver);
    }
}
