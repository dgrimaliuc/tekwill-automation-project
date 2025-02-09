package CMComponents.neonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCard extends Component {
@FindBy(css = "a[class*=title]")
public WebElement title;
@FindBy(css = "[class*=description]")
public WebElement description;

@FindBy(css = "img[class*=hero-card-picture]")
public WebElement image;

@FindBy(css = "button[class*=watch-now-btn]")
public WebElement watchNow;

@FindBy(css = "[data-t=not-in-watchlist]")
public WebElement addWatchList;

@FindBy(css = "[data-t=in-watchlist]")
public WebElement removeFromWatchList;
    public Component addToWatchList;

    public HeroCard(WebElement parent) {
        super(parent);
    }
}
