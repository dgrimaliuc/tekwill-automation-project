package example.components.neonStream;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WatchListButton extends Component {

    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement add;

    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement remove;

    public WatchListButton(ComponentContext context) {
        super(context);
    }
}
