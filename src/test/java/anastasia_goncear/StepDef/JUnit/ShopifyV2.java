package anastasia_goncear.StepDef.JUnit;

import anastasia_goncear.UI.Spotify.Pages.Components.SizeFilterSection;
import helpers.BasePage;
import internal.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyV2 extends BasePage {

    @FindBy(css = "[data-t='price-section']")
    public WebElement pricesFilterSection;

    @FindBy(css = ".card")
    public List<WebElement> cards;

    public ShopifyV2(WebDriver driver) {
        super(driver);
    }
    @FindBy(css="[data-t='size-section']")
    public SizeFilterSection sizeFilterSection;


}