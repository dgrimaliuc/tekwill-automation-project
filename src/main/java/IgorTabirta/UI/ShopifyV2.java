package IgorTabirta.UI;

import IgorTabirta.UI.Component.*;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShopifyV2 extends BasePage {

    @FindBy(css = "[data-t= 'price-section']")
    public PriceFilterSection PriceFilterSection;


    @FindBy(css = "[data-t='size-section']")
    public SizeFilterSection SizeFilterSection;

    @FindBy(css = "[data-t='color-section']")
    public ColorFilterSection ColorFilterSection;


    @FindBy(css = ".card")
    public List<WebElement> card;

    @FindBy(css = "[data-t='gender-section']")
    public GenderFilterSection genderFilters;

    @FindBy(css = ".card")
    public Components<Cards> cards;

    @FindBy(css = ".card")
    public AddToCart addToCart;

    public ShopifyV2(WebDriver driver) {
        super(driver);
    }


    public void open() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

}
