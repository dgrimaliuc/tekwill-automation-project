package Lilia_Rosca.poms.LR_NeonStream;

import Lilia_Rosca.components.LR_NeonStream.LR_Carousel;
import Lilia_Rosca.components.LR_NeonStream.LR_HeroCarousel;
import Lilia_Rosca.components.LR_NeonStream.LR_SingleCard;
import Lilia_Rosca.components.LR_NeonStream.LR_SinglePromoCard;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LR_HomePage extends BasePage {
    // 05.02
    @FindBy(css = "[class*=hero-carousel-wrapper]")
    public LR_HeroCarousel heroCarousel;

    // 07.02
    @FindBy(css = ".carousel-wrapper")
    public Components<LR_Carousel> carousel;

    @FindBy(css = ".single-promo-card-container")
    public Components<LR_SinglePromoCard> singlePromoCards;

// 10.02
    @FindBy(css = ".single-card-container")
    public LR_SingleCard singleCard;

    @FindBy(xpath = "//div[@class='carousel-wrapper' and .//*[text() = 'Your Watchlist']]")
    public LR_Carousel watchListCarousel;

    public LR_HomePage(WebDriver driver) {
        super(driver);
    }
}