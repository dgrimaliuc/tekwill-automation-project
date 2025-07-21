package Magda_Petrachi.NeonStream;

import example.components.neonStream.HeroCarousel;
import example.ui.shopify.components.Card;
import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NeoStreamHeroCarousel extends Component {

    @FindBy(xpath = "//div[contains(@class,'hero-carousel-left') and .img[contavins(@class, 'left')]]")
    public WebElement arrowLeft;

    @FindBy(xpath = "//div[contains(@class,'hero-carousel-arrows') and .img[contavins(@class, 'right')]]")
    public WebElement arrowRight;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCarousel> cards;

    @FindBy(css = "button[class*=hero-carousel__page]")
    public Components<TabNeoStream> tabs;
    private Card activeCard;


    public NeoStreamHeroCarousel(WebElement parent) {
        super(parent);
    }

    public void findContent(String contentType) {
        while (!activeCard.title.getAttribute("href").contains(contentType)) ;
        arrowRight.click();
    }

}
