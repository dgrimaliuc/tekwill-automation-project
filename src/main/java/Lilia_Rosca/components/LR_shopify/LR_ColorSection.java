package Lilia_Rosca.components.LR_shopify;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_ColorSection extends Component {
// 31.01
    @FindBy(css = "[value = 'Black']")
    public WebElement black;

    @FindBy(css = "[value = 'White']")
    public WebElement white;

    @FindBy(css = "[value = 'Red']")
    public WebElement red;

    @FindBy(css = "[value = 'Yellow']")
    public WebElement yellow;

    @FindBy(css = "[value = 'Green']")
    public WebElement green;

    @FindBy(css = "[value = 'Blue']")
    public WebElement blue;

    @FindBy(css = "[value = 'Purple']")
    public WebElement purple;

    @FindBy(css = "[value = 'Gray']")
    public WebElement gray;

    @FindBy(css = "[value = 'Beige']")
    public WebElement beige;

    @FindBy(css = "[value = 'Brown']")
    public WebElement brown;


    public LR_ColorSection(WebElement parent) {
        super(parent);
    }

    public static By getColor (String color) { // metoda pentru a crea xpath pentru mai multe culori, doar cu parametru
        return By.cssSelector("[value=" + color + "]");
    }
}
