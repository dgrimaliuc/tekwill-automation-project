package example.components.neonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleCard extends Component {


    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement watchNowButton;

    @FindBy(css = "div")
    public WatchListButton watchListButton;

    public SingleCard(WebElement parent) {
        super(parent);
    }
}
