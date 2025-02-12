package IonErm.poms.neon_app;

import IonErm.components.neon_app.ContentHeader;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SeriesPage extends BasePage {

    @FindBy(css = "picture[class*=background-picture]")
    public WebElement backPicture;

    @FindBy(css = "div[class*=content-header_content-header]")
    public ContentHeader contentHeader;

    public SeriesPage(WebDriver driver) {
        super(driver);
    }
}
