package CMComponents;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CMCard extends Component {

@FindBy(css = "#card-image")
public WebElement image;

@FindBy(css = "#card-title")
public WebElement title;

@FindBy(css = "#card-price")
public WebElement price;

@FindBy(css = "#card-color canvas")
public WebElement color;

@FindBy(css = "#card-size")
public WebElement size;

@FindBy(css = "#add_to_cart_button")
public WebElement addToCart;


    public CMCard(WebElement parent) {
        super(parent);
    }


}
