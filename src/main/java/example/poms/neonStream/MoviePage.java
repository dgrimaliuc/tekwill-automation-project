package example.poms.neonStream;

import example.components.neonStream.ContentHeader;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MoviePage extends BasePage {

    @FindBy(css = "picture[class*=background-picture]")
    public WebElement backImage;

    @FindBy(css = "div[class*=content-header_content-header]")
    public ContentHeader contentHeader;

    public MoviePage(WebDriver driver) {
        super(driver);
    }
}
