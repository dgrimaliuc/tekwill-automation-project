package lilia_toma.NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseCard extends Component {

    @FindBy (css = "[class*=browse-card-poster]")
    public WebElement image;

    @FindBy (css = "[class*=browse-card_media_type]")
    public WebElement mediaType;

    @FindBy (css = "[class*=browse-card_browse-card-info]")
    public WebElement title;

    public BrowseCard(WebElement parent) {
        super(parent);
    }
}
