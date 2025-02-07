package denis_grimaliuc.poms.neonStream;

import denis_grimaliuc.components.neonStream.Carousel;
import denis_grimaliuc.components.neonStream.HeroCarousel;
import denis_grimaliuc.components.neonStream.SinglePromoCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "[class*=hero-carousel-wrapper]")
    public HeroCarousel heroCarousel;

    @FindBy(css = ".carousel-wrapper")
    public Components<Carousel> carousel;

    @FindBy(css = ".single-promo-card-container")
    public Components<SinglePromoCard> singlePromoCards;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
