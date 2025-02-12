package IonErm.components.neon_app;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WatchListBtn extends Component {

    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement add;

    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement remove;

    public WatchListBtn(WebElement parent) {
        super(parent);
    }
}
