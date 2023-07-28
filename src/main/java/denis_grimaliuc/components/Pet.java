package denis_grimaliuc.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Pet extends Component {

    @FindBy(xpath = ".//td[2]")
    public WebElement status;

    @FindBy(xpath = ".//td[1]")
    public WebElement name;

}
