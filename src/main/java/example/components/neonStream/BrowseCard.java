package example.components.neonStream;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseCard extends Component {
    @FindBy(css = "img[class*=browse-card-poster]")
    public WebElement poster;

    @FindBy(css = "[class*=browse-card_browse-card-info]")
    public WebElement title;

    public BrowseCard(ComponentContext context) {
        super(context);
    }
}
