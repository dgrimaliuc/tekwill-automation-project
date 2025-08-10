package denis_grimaliuc.neonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NoResultsSearchSection extends Component {

    @FindBy(css = "[class*=search_stars]")
    public WebElement stars;

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[class*=search_description]")
    public WebElement description;

    public NoResultsSearchSection(WebElement parent) {
        super(parent);
    }
}
