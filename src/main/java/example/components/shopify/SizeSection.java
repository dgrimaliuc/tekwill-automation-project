package example.components.shopify;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SizeSection extends Component {

    @FindBy(css = "[value=S]")
    public WebElement s;
    @FindBy(css = "[value=XL]")
    public WebElement xl;

    public SizeSection(ComponentContext context) {
        super(context);
    }

    public static By getSize(String size) {
        return By.cssSelector("[value=" + size + "]");
    }
}
