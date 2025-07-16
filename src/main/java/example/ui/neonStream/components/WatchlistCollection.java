package example.ui.neonStream.components;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WatchlistCollection extends Component {

    @FindBy(css = "[class*=browse-card-info] > p")
    public List<WebElement> titles;

    public WatchlistCollection(ComponentContext context) {
        super(context);
    }
}
