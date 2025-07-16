package denis_grimaliuc.neonStream;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NeonStreamPage extends BasePage {

    @FindBy(css = "[class*=styles_hero-carousel-wrapper]")
    public HeroCarousel heroCarousel;

    @FindBy(css = "[class*=placeholder]")
    public List<WebElement> placeholders;

    public NeonStreamPage(WebDriver driver) {
        super(driver);
    }
}
