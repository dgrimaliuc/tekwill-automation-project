package Lilia_Rosca.components.LR_shopify;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_GenderSection extends Component {

// 31.01
    @FindBy(css = "[value = Female]")
    public WebElement female;

    @FindBy(css = "[value = Male]")
    public WebElement male;

    public static By getGender (String gender) { // metoda pentru a crea xpath pentru mai multe culori, doar cu parametru
        return By.cssSelector("[value=" + gender + "]");
    }

    public LR_GenderSection(WebElement parent) {
        super(parent);
    }
}
