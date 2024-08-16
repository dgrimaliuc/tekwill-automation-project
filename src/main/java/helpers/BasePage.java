package helpers;

import helpers.customElements.factories.CustomPageFactory;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        CustomPageFactory.initElements(driver, this, null);
    }
}
