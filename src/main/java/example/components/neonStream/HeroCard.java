package example.components.neonStream;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
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

    @FindBy(css = "div")
    public WatchListButton watchListButton;

    public HeroCard(ComponentContext context) {
        super(context);
    }
}
