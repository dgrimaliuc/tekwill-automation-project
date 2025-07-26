package Magda_Petrachi.NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseCard extends Component {

    @FindBy(css = "[class*=browse-card-poster]")
    public WebElement image;

    @FindBy(css = "[class*=media-type]")
    public WebElement mediaType;

    @FindBy(css = "[class*=browse-card-info]")
    public WebElement title;


    public BrowseCard(WebElement parent) {
        super(parent);
    }
}
