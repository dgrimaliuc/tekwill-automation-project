package IonErm.components.neon_app;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleCard extends Component {

    @FindBy(css = "[class*=actions_watch-now-btn]")
    public WebElement watchNowBtn;

    @FindBy(css = "div")
    public WatchListBtn watchListButton;
    
    public SingleCard(WebElement parent) {
        super(parent);
    }
}
