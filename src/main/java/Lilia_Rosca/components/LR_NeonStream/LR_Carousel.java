package Lilia_Rosca.components.LR_NeonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_Carousel extends Component {
    // 07.02
    @FindBy(css = ".carousel-title")
    public WebElement title;

    @FindBy(css = "[class*=controls_arrow_left]")
    public WebElement leftArrow;

    @FindBy(css = "[class*=controls_arrow_right]")
    public WebElement rightArrow;

    @FindBy(css = ".scrolling-section > div")
    public Components<LR_BrowseCard> browseCards;


    public LR_Carousel(WebElement parent) {super(parent);}
}