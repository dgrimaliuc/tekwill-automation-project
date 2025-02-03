package Lilia_Rosca.components.LR_shopify;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_SizeSection extends Component {
// 31.01
    @FindBy(css = "[value = 'S']")
    public WebElement s;

    @FindBy(css = "[value = 'M']")
    public WebElement m;

    @FindBy(css = "[value = 'L']")
    public WebElement l;

    @FindBy(css = "[value = 'XL']")
    public WebElement xl;

    public static By getSize (String size) { // metoda pentru a crea xpath pentru mai multe culori, doar cu parametru
        return By.cssSelector("[value=" + size + "]");
    }

    public LR_SizeSection(WebElement parent) {
        super(parent);
    }
}
