package denis_grimaliuc.components.neonStream;

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
    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement addToWathList;
    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement removeFromWathList;

    public HeroCard(WebElement parent) {
        super(parent);
    }
}
