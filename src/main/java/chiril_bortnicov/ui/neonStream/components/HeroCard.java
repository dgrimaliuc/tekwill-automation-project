package chiril_bortnicov.ui.neonStream.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCard extends Component {

    @FindBy(css = "[class*=hero-card_title]")
    public WebElement title;

    @FindBy(css = "[class*=hero-card_description]")
    public WebElement description;

    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement watchNowButton;
    
    @FindBy(css = "[class*=watchlist-button]")
    public WebElement watchlistButton;

    @FindBy(css = "[class*=hero-card-picture]")
    public WebElement backgroundImage;

    public HeroCard(WebElement parent) {
        super(parent);
    }
}
