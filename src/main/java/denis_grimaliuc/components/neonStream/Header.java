package denis_grimaliuc.components.neonStream;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {

    @FindBy(css = "[class*=navigation-items] a[href='/watchlist']")
    public WebElement watchListTab;
    @FindBy(css = "[class*=navigation-items] a[href='/']")
    public WebElement homeTab;


    public Header(WebDriver driver) {
        super(driver);
    }
}
