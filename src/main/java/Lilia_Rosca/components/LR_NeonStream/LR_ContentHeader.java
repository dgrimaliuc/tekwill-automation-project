package Lilia_Rosca.components.LR_NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LR_ContentHeader extends Component {
// 10.02
    @FindBy (css = "[class*=header_title]")
    public WebElement title;

    @FindBy (css = "div[class*=tags] [class*=tag]")
    public List<WebElement> tags;

    @FindBy (css = "[class*=watch-now-btn]")
    public WebElement watchNowBtn;

    @FindBy (css = "[class*=actions_actions]")
    public LR_WatchlistButton watchlistButton;

    public LR_ContentHeader(WebElement parent) {
        super(parent);
    }
}
