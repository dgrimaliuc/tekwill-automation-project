package denis_grimaliuc.poms.neonStream;

import denis_grimaliuc.components.neonStream.HeroCarousel;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "[class*=hero-carousel-wrapper]")
    public HeroCarousel heroCarousel;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
