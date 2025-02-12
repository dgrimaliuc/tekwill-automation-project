package IonErm.components.neon_app;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContentHeader extends Component {

    @FindBy(css = "[class*=content-header_title]")
    public WebElement title;

    @FindBy(css = "[class*='tags_tags'] [class*='tag']")
    public List<WebElement> tags;

    @FindBy(css = "[class*=actions_watch-now]")
    public WebElement watchNow;

    @FindBy(css = "[class*=actions_actions]")
    public WatchListBtn watchListButton;

    public ContentHeader(WebElement parent) {
        super(parent);
    }
}
