package andrei.marcenco.ui.shopify;

import andrei.marcenco.ui.componentsAM.PriceFilterAM;
import andrei.marcenco.ui.componentsAM.SizeFilterAM;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyAM extends BasePage {

    public ShopifyAM(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-t='price-section']")
    public PriceFilterAM priceFilterAM;

    @FindBy(css = ".card")
    public static List<WebElement> cardsPrices;


    @FindBy(css = "[data-t='size-section']")
    public SizeFilterAM sizeFilterAM;

    @FindBy(css = ".card")
    public static List<WebElement> cardsSizes;

//    Create a test for adding cart item in cart using Junit + new Page object Model

    @FindBy(css = "[id=add_to_cart_button]")
    public WebElement addToCart;
}
