package Lilia_Rosca.components.LR_NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_SinglePromoCard extends Component {
    // 07.02
    @FindBy(css = ".single-card-img-wrapper .image-right")
    public WebElement image;

    @FindBy(css = ".single-show-card-title")
    public WebElement title;

    @FindBy(css = ".single-show-card-description")
    public WebElement description;

    @FindBy(css = "[class*=actions_watch-now-btn]")
    public WebElement watchNow;

    @FindBy(css ="[data-t=not-in-watchlist]")
    public WebElement addToWatchList;

    @FindBy(css ="[data-t=in-watchlist]")
    public WebElement removeFromWatchList;


    public LR_SinglePromoCard(WebElement parent) {super(parent);}
}
