package Lilia_Rosca.components.LR_NeonStream;

import helpers.BasePage;
import helpers.customElements.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_Footer extends BasePage {
// 12.02
    @FindBy (css = ".footer-container")
    public WebElement container;

    public LR_Footer(WebDriver driver) {super(driver);}
}
