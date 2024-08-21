package andrei.marcenco.ui.componentsAM;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SizeFilterAM extends Component {
    public SizeFilterAM(WebElement parent) {
        super(parent);
    }

    @FindBy(css = "input[value='S']")
    public WebElement s;
    @FindBy(css = "input[value='M']")
    public WebElement m;
    @FindBy(css = "input[value='L']")
    public WebElement l;
    @FindBy(css = "input[value='XL']")
    public WebElement xl;
}
