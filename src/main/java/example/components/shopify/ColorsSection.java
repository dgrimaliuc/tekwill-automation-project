package example.components.shopify;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ColorsSection extends Component {

    @FindBy(css = "[value=Black]")
    public WebElement black;
    @FindBy(css = "[value=Blue]")
    public WebElement blue;
    @FindBy(css = "[value=White]")
    public WebElement white;
    @FindBy(css = "[value=Red]")
    public WebElement red;
    @FindBy(css = "[value=Yellow]")
    public WebElement yellow;
    @FindBy(css = "[value=Green]")
    public WebElement green;
    @FindBy(css = "[value=Purple]")
    public WebElement purple;
    @FindBy(css = "[value=Gray]")
    public WebElement gray;
    @FindBy(css = "[value=Beige]")
    public WebElement beige;
    @FindBy(css = "[value=Brown]")
    public WebElement brown;

    public ColorsSection(ComponentContext context) {
        super(context);
    }

    public static By getColor(String color) {
        return By.cssSelector("[value=" + color + "]");
    }
}
