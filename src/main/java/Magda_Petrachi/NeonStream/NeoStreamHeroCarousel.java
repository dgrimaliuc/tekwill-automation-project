package Magda_Petrachi.NeonStream;

import example.components.neonStream.HeroCarousel;
import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NeoStreamHeroCarousel extends Component {

    @FindBy(css = "[class*=arrow-left]")
    public WebElement arrowLeft;

    @FindBy(css = "[class*=arrow-right]")
    public WebElement arrowRight;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCarousel> cards;

    @FindBy(css = "button[class*=hero-carousel__page]")
    public List<WebElement> tabs;


    public NeoStreamHeroCarousel(WebElement parent) {
        super(parent);
    }
}
