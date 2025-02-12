package IonErm.components.neon_app;

import helpers.BasePage;
import helpers.customElements.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {

    @FindBy(css = "[class*=navigation-items] a[href='/watchlist']")
    public WebElement watchlistTab;

    @FindBy(css = "[class*=navigation-items] a[href='/']")
    public WebElement homeTab;

    public Header(WebDriver driver) {
        super(driver);
    }
}
