package victor_murashev.junit.neonStream.pages;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import victor_murashev.junit.neonStream.components.HeroCarousel;

public class NeonStreamHomePage extends BasePage {

    @FindBy(css = ".hero-cards-wrapper")
    public HeroCarousel cards;

    public NeonStreamHomePage(WebDriver driver) {
        super(driver);
    }
}
