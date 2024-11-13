package example.ui.neonStream.pages;

import example.ui.neonStream.components.HeroCarousel;
import example.ui.neonStream.components.WatchlistCollection;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NeonStreamHomePage extends BasePage {

    @FindBy(xpath = "//div[@class='carousel-wrapper' and .//a[@href='/watchlist']]")
    public WatchlistCollection watchlistCollection;

    @FindBy(css = ".hero-carousel-wrapper")
    public HeroCarousel heroCarousel;

    @FindBy(css = ".single-promo-card-container")
    public WebElement promoCard;
    @FindBy(css = ".single-card")
    public WebElement singleCard;

    public NeonStreamHomePage(WebDriver driver) {
        super(driver);
    }


    public void openPage() {
        driver.get("https://neon-stream--stage-j0k6u8j7.web.app/");
    }
}
