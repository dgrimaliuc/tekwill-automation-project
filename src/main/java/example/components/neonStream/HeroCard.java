package example.components.neonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCard extends Component {
    @FindBy(css = "a[class*=title]")
    public WebElement title;

    @FindBy(css = "img[class*=hero-card-picture]")
    public WebElement image;

    @FindBy(css = "[class*=description]")
    public WebElement description;
    @FindBy(css = "button[class*=watch-now-btn]")
    public WebElement watchNow;

    @FindBy(css = ".actions_watchlist-button__EAhGY.actions_default-button__S42R6")
    public WatchListButton watchListButton;

    @FindBy(css = ".actions_wl_container__B3VYN")
    public WatchListButton removeListButton;


    public HeroCard(WebElement parent) {
        super(parent);
    }
}
