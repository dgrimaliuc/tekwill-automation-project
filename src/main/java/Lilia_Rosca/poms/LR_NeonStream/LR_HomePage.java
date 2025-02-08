package Lilia_Rosca.poms.LR_NeonStream;

import Lilia_Rosca.components.LR_NeonStream.LR_HeroCarousel;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LR_HomePage extends BasePage {
// 05.02
    @FindBy(css = "[class*=hero-carousel-wrapper]")
    public LR_HeroCarousel heroCarousel;

// 07.02
    // LR_carousel
    // LR_SinglePromoCard




    public LR_HomePage(WebDriver driver) {
        super(driver);
    }

}