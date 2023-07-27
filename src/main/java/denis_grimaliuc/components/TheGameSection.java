package denis_grimaliuc.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TheGameSection extends Component {

    @FindBy(xpath = "./p[2]")
    public WebElement petsInInfo;

    @FindBy(xpath = "./p[3]")
    public WebElement adoptsInfo;
}
