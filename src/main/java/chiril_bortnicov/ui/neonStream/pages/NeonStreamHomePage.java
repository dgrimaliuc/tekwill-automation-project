package chiril_bortnicov.ui.neonStream.pages;

import chiril_bortnicov.ui.neonStream.components.HeroCarousel;
import chiril_bortnicov.ui.neonStream.components.WatchlistCollection;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class NeonStreamHomePage extends BasePage {

    @FindBy(xpath = "//div[@class='carousel-wrapper' and .//a[@href='/watchlist']]")
    public WatchlistCollection watchlistCollection;

    @FindBy(css = ".hero-carousel-wrapper")
    public HeroCarousel heroCarousel;

    public void openPage() {
        driver.get("https://neon-stream--stage-j0k6u8j7.web.app/");
    }

    public NeonStreamHomePage(WebDriver driver) {
        super(driver);
    }
}
