package denis_grimaliuc.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Adoptions extends Component {

    @FindBy(css = "div.text-gray-600")
    public List<WebElement> petNames;

    public Adoptions(WebElement parent) {
        super(parent);
    }
}
