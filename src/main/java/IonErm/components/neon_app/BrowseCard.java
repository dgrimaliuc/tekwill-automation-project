package IonErm.components.neon_app;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseCard extends Component {

    @FindBy(css = "img[class*=browse-card-poster]")
    public WebElement picture;

    @FindBy(css = "[class*=browse-card-info]")
    public WebElement title;

    public BrowseCard(WebElement parent) {
        super(parent);
    }
}
