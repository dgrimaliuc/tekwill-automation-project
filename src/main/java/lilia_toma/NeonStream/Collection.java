package lilia_toma.NeonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Collection extends Component {

    @FindBy (css = ".carousel-title")
    public WebElement title;

    @FindBy (css = ".scrolling-section > div:has([class*=browse-card])")
    public Components<BrowseCard> cards;




    public Collection(WebElement parent) {
        super(parent);
    }
}
