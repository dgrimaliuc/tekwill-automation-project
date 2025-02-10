package denis_grimaliuc.components.neonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContentHeader extends Component {
    @FindBy(css = "[class*=header_title]")
    public WebElement title;

    @FindBy(css = "div[class*=tags] [class*=tag]")
    public List<WebElement> tags;

    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement watchNowBtn;

    @FindBy(css = "[class*=actions_actions]")
    public WatchListButton watchListButton;

    public ContentHeader(WebElement parent) {
        super(parent);
    }
}
