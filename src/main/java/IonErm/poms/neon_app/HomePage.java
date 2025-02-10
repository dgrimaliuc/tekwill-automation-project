package IonErm.poms.neon_app;

import IonErm.components.neon_app.Carousel;
import IonErm.components.neon_app.HeroCard;
import IonErm.components.neon_app.HeroCarousel;
import IonErm.components.neon_app.SinglePromoCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "[class*=styles_hero-carousel]")
    public HeroCarousel heroCarousel;

    @FindBy(css = ".carousel-wrapper")
    public Components<Carousel> carousel;

    @FindBy(css = ".single-promo-card")
    public Components<SinglePromoCard> singlePromoCards;


    public HomePage(WebDriver driver) {
        super(driver);
    }
}
