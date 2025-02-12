package IonErm.poms.neon_app;

import IonErm.components.neon_app.Carousel;
import IonErm.components.neon_app.HeroCarousel;
import IonErm.components.neon_app.SingleCard;
import IonErm.components.neon_app.SinglePromoCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "[class*=styles_hero-carousel]")
    public HeroCarousel heroCarousel;

    @FindBy(xpath = "//div[@class='carousel-wrapper' and .//*[text()='Your Watchlist']]")
    public Carousel watchListCarousel;

    @FindBy(css = ".carousel-wrapper")
    public Components<Carousel> carousel;

    @FindBy(css = ".single-promo-card")
    public Components<SinglePromoCard> singlePromoCards;

    @FindBy(css = ".single-card-container")
    public SingleCard singleCards;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
