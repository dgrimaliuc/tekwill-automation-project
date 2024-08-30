package victor_murashev.ui.neonStream.pages;

import denis_grimaliuc.ui.neonStream.components.HeroCarousel;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class NeonStreamHomePage extends BasePage {

    @FindBy(css = ".hero-carousel-wrapper")
    public HeroCarousel heroCarousel;

    public NeonStreamHomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://neon-stream--stage-j0k6u8j7.web.app/");


    }
}
