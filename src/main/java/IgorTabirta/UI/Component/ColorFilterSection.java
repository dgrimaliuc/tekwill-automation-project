package IgorTabirta.UI.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ColorFilterSection extends Component {

    @FindBy(css = "input[value='Black']")
    public WebElement Black;
    @FindBy(css = "input[value='White']")
    public WebElement White;
    @FindBy(css = "input[value='Red']")
    public WebElement Red;
    @FindBy(css = "input[value='Yellow']")
    public WebElement Yellow;
    @FindBy(css = "input[value='Green']")
    public WebElement Green;
    @FindBy(css = "input[value='Blue']")
    public WebElement Blue;
    @FindBy(css = "input[value='Purple']")
    public WebElement Purple;
    @FindBy(css = "input[value='Gray']")
    public WebElement Gray;
    @FindBy(css = "input[value='Beige']")
    public WebElement Beige;
    @FindBy(css = "input[value='Brown']")
    public WebElement Brown;


    public ColorFilterSection(WebElement parent) {
        super(parent);
    }

}
