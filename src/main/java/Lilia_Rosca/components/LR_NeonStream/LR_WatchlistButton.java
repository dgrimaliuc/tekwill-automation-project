package Lilia_Rosca.components.LR_NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_WatchlistButton extends Component {
// 10.02
    @FindBy (css = "[data-t = not-in-watchlist]")
    public WebElement add;

    @FindBy (css = "[data-t = in-watchlist]")
    public WebElement remove;

    public LR_WatchlistButton(WebElement parent) {
        super(parent);
    }
}
