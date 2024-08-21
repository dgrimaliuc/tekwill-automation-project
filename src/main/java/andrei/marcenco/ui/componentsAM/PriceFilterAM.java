package andrei.marcenco.ui.componentsAM;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PriceFilterAM extends Component {

    public PriceFilterAM(WebElement parent) {
        super(parent);
    }

    @FindBy(css = "input[value='Under $25']")
    public WebElement under25;
    @FindBy(css = "input[value='$25 to $50']")
    public WebElement from25to50;
    @FindBy(css = "input[value='$50 to $100']")
    public WebElement from50to100;
    @FindBy(css = "input[value='Over $100']")
    public WebElement over100;


}

