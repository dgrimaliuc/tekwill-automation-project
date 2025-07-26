package Magda_Petrachi.NeonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Collections extends Component {

    @FindBy(css = ".carousel-title")
    public List<WebElement> title;

    @FindBy(css = "[class*=arrow-left]")
    public WebElement arrowLeft;

    @FindBy(css = "[class*=arrow-right]")
    public WebElement arrowRight;


    @FindBy(css = ".scrolling-section > div:has([class*=browse-card])")
    public Components<BrowseCard> cards;


    public Collections(WebElement parent) {
        super(parent);
    }
}
