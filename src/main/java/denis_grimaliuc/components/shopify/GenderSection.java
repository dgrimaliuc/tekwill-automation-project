package denis_grimaliuc.components.shopify;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenderSection extends Component {
    @FindBy(css = "[value=Male]")
    public WebElement male;
    @FindBy(css = "[value=Female]")
    public WebElement female;

    public GenderSection(WebElement parent) {
        super(parent);
    }

    public static By getGender(String gender) {
        return By.cssSelector("[value=" + gender + "]");
    }
}
