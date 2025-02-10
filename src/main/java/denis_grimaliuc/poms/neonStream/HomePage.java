package denis_grimaliuc.poms.neonStream;

import denis_grimaliuc.components.neonStream.Carousel;
import denis_grimaliuc.components.neonStream.HeroCarousel;
import denis_grimaliuc.components.neonStream.SingleCard;
import denis_grimaliuc.components.neonStream.SinglePromoCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "[class*=hero-carousel-wrapper]")
    public HeroCarousel heroCarousel;

    @FindBy(xpath = "//div[@class='carousel-wrapper' and .//*[text()='Your Watchlist']]")
    public Carousel watchListCarousel;

    @FindBy(css = ".carousel-wrapper")
    public Components<Carousel> carousel;

    @FindBy(css = ".single-promo-card-container")
    public Components<SinglePromoCard> singlePromoCards;

    @FindBy(css = ".single-card-container")
    public SingleCard singleCard;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
