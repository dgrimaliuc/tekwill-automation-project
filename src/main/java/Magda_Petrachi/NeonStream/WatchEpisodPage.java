package Magda_Petrachi.NeonStream;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WatchEpisodPage extends BasePage {

    @FindBy(css = ".content-title")
    public WebElement title;


    public WatchEpisodPage(WebDriver driver) {
        super(driver);
    }
}