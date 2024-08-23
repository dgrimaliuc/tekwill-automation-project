package victor_murashev.ui.shopify.pages;

import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import victor_murashev.ui.shopify.components.InventorySection;
import victor_murashev.ui.shopify.components.SauceCartSection;

public class SaucePOM extends BasePage {

    @FindBy(css = ".inventory_list")
    public InventorySection inventorySection;

    // Prices
    @FindBy(css = ".inventory_item_price")
    public WebElement inventoryPrices;

    @FindBy(css = ".inventory_item")
    public Components<InventorySection> inventories;

    @FindBy(css = ".shopping_cart_link")
    public WebElement cartIcon;

    @FindBy(css = ".cart_list")
    public SauceCartSection sauceCartSection;

    public SaucePOM(WebDriver driver) {
        super(driver);
    }

    public void openSauceEntry() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input#password")).sendKeys("secret_sauce");
    }
}
