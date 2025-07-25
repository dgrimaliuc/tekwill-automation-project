package denis_grimaliuc.neonStream;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCarousel extends Component {
    @FindBy(xpath = "//div[contains(@class,'hero-carousel-arrows') and  .//img[contains(@class,'left')]]")
    public WebElement arrowLeft;
    @FindBy(xpath = "//div[contains(@class,'hero-carousel-arrows') and  .//img[contains(@class,'right')]]")
    public WebElement arrowRight;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCard> cards;

    @FindBy(css = "[class*=styles_active][class*=hero-card-container]")
    public HeroCard activeCard;

    @FindBy(css = "button[class*=hero-carousel__page]")
    public Components<Tab> tabs;

    public HeroCarousel(WebElement parent) {
        super(parent);
    }

    public void findContent(String contentType) {
        while (!activeCard.title.getAttribute("href").contains(contentType)) {
            arrowRight.click();
        }
    }
}
