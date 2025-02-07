package IonErm.poms.neon_app;

import IonErm.components.neon_app.HeroCard;
import IonErm.components.neon_app.HeroCarousel;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "[class*=styles_hero-carousel]")
    public HeroCarousel heroCarousel;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
