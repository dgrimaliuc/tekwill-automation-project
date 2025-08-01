package Magda_Petrachi.NeonStream;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContentPage extends BasePage {

    @FindBy(css = "[class*=content-header_title]")
    public WebElement title;


    public ContentPage(WebDriver driver) {
        super(driver);
    }
}