package CMComponents;


import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PriceSectionShopify extends Component {

    @FindBy (css = "[value='Under $25']" )
    public WebElement under25;

    @FindBy (css = "[value='$25 to $50']" )

    public WebElement _25to50;

    @FindBy (css = "[value='$50 to $100']" )
    public WebElement _50to100;

    @FindBy (css = "[value='Over $100']" )
    public WebElement over100;


    public PriceSectionShopify(WebElement parent) {
        super(parent);
    }

}
