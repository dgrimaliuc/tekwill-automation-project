package Magda_Petrachi.Shopify;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CardShopify extends Component {

    @FindBy(css = "#card_title")
    public WebElement title;

    @FindBy(css = "#card_color canvas")
    public WebElement color;

    @FindBy(css = "#card_price")
    public WebElement price;

    @FindBy(css = "#card_image")
    public WebElement image;

    @FindBy(css = "#card_size")
    public WebElement size;

    @FindBy(css = "#card_gender")
    public WebElement gender;

    @FindBy(css = "#add_to_card_button")
    public WebElement addToCardButton;

    @FindBy(css = "#card_button")
    public WebElement CardButton;

    public WebElement addToCartButton;


    public CardShopify(List components) {
        super((WebElement) components);
    }
}
