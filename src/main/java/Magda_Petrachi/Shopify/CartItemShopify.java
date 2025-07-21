package Magda_Petrachi.Shopify;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartItemShopify extends BasePage {

    @FindBy(css = ".cart-card-image")
    public WebElement image;

    @FindBy(css = ".cart-card-info h4")
    public WebElement title;

    @FindBy(css = ".cart-card-info p:nth-child(2)")
    public WebElement price;

    @FindBy(css = ".cart-card-info p:nth-child(3)")
    public WebElement color;

    @FindBy(css = ".cart-card-info p:nth-child(4)")
    public WebElement size;
    @FindBy(css = ".remove-item")
    public WebElement remove;
    @FindBy(css = ".minus-item")
    public WebElement minus;

    @FindBy(css = ".plus-item")
    public WebElement plus;

    @FindBy(css = ".item-quantity")
    public WebElement quantity;

    public CartItemShopify(WebElement parent) {
        super((WebDriver) parent);
    }
}
