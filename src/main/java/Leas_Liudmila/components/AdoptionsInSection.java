package Leas_Liudmila.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdoptionsInSection extends Component {
    @FindBy(xpath = ".//h2")
    public WebElement title;

    @FindBy(xpath = "./span")
    public WebElement status;

    @FindBy(css = "div.text-gray-600")
    public List<WebElement> petName;

    @FindBy(xpath = "//button[text()=' Approve ']")
    public WebElement approveBtn;

    @FindBy(xpath = "//button[text()=' Deny ']")
    public WebElement denyBtn;

    public AdoptionsInSection(WebElement parent) {
        super(parent);
    }
}
