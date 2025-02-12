package cristina_mocanu_poms.neonStream;

import CMComponents.neonStream.Carousel;
import CMComponents.neonStream.HeroCarousel;
import CMComponents.neonStream.SinglePromoCard;
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
    public Components<SinglePromoCard> singlePromoCard;



    public HomePage(WebDriver driver) {
        super(driver);
    }
}
