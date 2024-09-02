package IgorTabirta.UI.Neon.Component;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WatchListCollection extends Component {

    @FindBy(css = "[class*=browse-card-info] > p")
    public List<WebElement> titles;

    public WatchListCollection(WebElement parent) {
        super(parent);
    }
}
