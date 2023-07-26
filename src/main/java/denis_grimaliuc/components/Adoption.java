package denis_grimaliuc.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Adoption extends Component {

    @FindBy(css = "div.text-gray-600")
    public List<WebElement> petNames;

    @FindBy(xpath = ".//button[text()=' Approve ']")
    public WebElement approve;

    @FindBy(xpath = ".//button[text()=' Deny ']")
    public WebElement deny;

    @FindBy(xpath = ".//h2/span")
    public WebElement title;

    @FindBy(xpath = "./span")
    public WebElement status;
}
