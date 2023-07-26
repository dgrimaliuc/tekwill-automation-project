package Leas_Liudmila.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdoptionsInSection extends Component {
    @FindBy(xpath = ".//div//h2")
    public List<WebElement> title;

    @FindBy(xpath = "./div/div/div/span")
    public List<WebElement> status;

    @FindBy(css = "div.text-gray-600")
    public List<WebElement> petName;

    @FindBy(xpath = "//button[text()=' Approve ']")
    public List<WebElement> approveBtn;

    @FindBy(xpath = "//button[text()=' Deny ']")
    public List<WebElement> denyBtn;


}
