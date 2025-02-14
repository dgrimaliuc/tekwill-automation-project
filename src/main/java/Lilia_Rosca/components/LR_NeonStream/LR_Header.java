package Lilia_Rosca.components.LR_NeonStream;

import helpers.BasePage;
import helpers.customElements.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_Header extends BasePage {
// 10.02
    @FindBy (css = "[class*=navigation-items] a[href ='/']")
    public WebElement homeTab;

    @FindBy (css = "[class*=navigation-items] a[href ='/watchlist']")
    public WebElement watchlistTab;

// 12.02
    @FindBy (css = "[class*=search_container] input")
    public WebElement searchInput;

    public LR_Header(WebDriver driver) {
        super(driver);
    }
}