package lilia_toma.Shopify;

import example.components.shopify.PriceSection;
import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.html.HTMLInputElement;

public class CardPrice extends Component {
    @FindBy(css = "[value*='Under $25']")
    public WebElement priceUnder25C;

    @FindBy(css = "[value*='$25 to $50']")
    public WebElement price25To50C;

    @FindBy(css = "[value*='$50 to $100']")
    public WebElement price50To100C;

    @FindBy(css = "[value*='Over $100']")
    public WebElement priceOver100C;


    public CardPrice(WebElement parent) {
        super(parent);
    }

}

