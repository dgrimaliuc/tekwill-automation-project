package IonErm.components.shopify;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SizeSection extends Component {

    @FindBy(css = "[value='S']")
    public WebElement S;
    @FindBy(css = "[value='M']")
    public WebElement M;
    @FindBy(css = "[value='L']")
    public WebElement L;
    @FindBy(css = "[value='XL']")
    public WebElement XL;

    public SizeSection(WebElement parent) {
        super(parent);
    }

    public static By getSize(String size) {
        return By.cssSelector("[value='" + size + "']");
    }
}
