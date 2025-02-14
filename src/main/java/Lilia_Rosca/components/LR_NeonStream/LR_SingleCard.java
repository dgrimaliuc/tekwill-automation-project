package Lilia_Rosca.components.LR_NeonStream;

import denis_grimaliuc.components.neonStream.WatchListButton;
import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_SingleCard extends Component {
//10.02
    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement WatchNowButton;

    @FindBy(css = "div")
    public WatchListButton watchListButton;

    public LR_SingleCard(WebElement parent) {super(parent);}
}
