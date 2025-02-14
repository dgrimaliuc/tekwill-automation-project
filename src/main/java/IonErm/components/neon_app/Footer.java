package IonErm.components.neon_app;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Footer extends BasePage {

    @FindBy(css = ".footer-container")
    public WebElement container;

    public Footer(WebDriver driver) {
        super(driver);
    }
}
