package Lilia_Rosca.components.LR_NeonStream;

import Lilia_Rosca.poms.LR_NeonStream.LR_HomePage;
import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LR_HeroCarousel extends Component {
    // 05.02
    @FindBy(css = "[class*=pagination-wrapper] button[class*=hero-carousel__page]")
    public Components<LR_HCTab> tabs;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<LR_HeroCard> cards;

    @FindBy(css = "[class*=hero-card-container]:not([class*=inactive])")
    public LR_HeroCard activeCard;

    @FindBy(css = "[class*=arrow-left]")
    public WebElement leftArrow;

    @FindBy(css = "[class*=arrow-right]")
    public WebElement rightArrow;

    @FindBy(css = "[class*=placeholder]")
    public List<WebElement> placeHolders;


    public LR_HeroCarousel(WebElement parent) {
        super(parent);
    }
}
