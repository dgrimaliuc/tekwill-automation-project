package cristina_mocanu_poms;

import CMComponents.CMCard;
import CMComponents.PriceSectionShopify;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ShopifyPage extends BasePage {

 @FindBy(css = "#dropdownButton")
 public WebElement dropdownButton;

 @FindBy(css = "#dropdownList li:nth-child(1)")
 public WebElement ascendingOrder;

 @FindBy(css = "#dropdownList li:nth-child(2)")
 public WebElement descendingOrder;

 @FindBy(css = "[data-t=price-section]")
 public PriceSectionShopify priceSection;

 @FindBy(css = "[data-t=color-section]")
 public WebElement colorSection;

 @FindBy(css = "[data-t=size-section]")
 public WebElement sizeSection;

 @FindBy(css = "[data-t=gender-section]")
 public WebElement genderSection;

 @FindBy(css = ".card")
public Components <CMCard> cards;

    public ShopifyPage(WebDriver driver) {
        super(driver);
    }
}
