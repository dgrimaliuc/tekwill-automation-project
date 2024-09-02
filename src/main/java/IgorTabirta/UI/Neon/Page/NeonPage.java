package IgorTabirta.UI.Neon.Page;

import IgorTabirta.UI.Neon.Component.HeroCarousel;
import IgorTabirta.UI.Neon.Component.WatchListCollection;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NeonPage extends BasePage {

    @FindBy(css = ".hero-carousel-wrapper")
    public HeroCarousel heroCarousel;

    @FindBy(xpath = "//div[@class='carousel-wrapper' and .//a[@href='/watchlist']]")
    public WatchListCollection watchListCollection;

    public void openNeonpage() {
        driver.get("https://neon-stream--stage-j0k6u8j7.web.app/");
    }

    @FindBy(css = ".single-promo-card-container")
    public WebElement promoCard;


    public NeonPage(WebDriver driver) {
        super(driver);
    }
}
