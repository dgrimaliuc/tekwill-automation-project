package denis_grimaliuc.neonStream;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NeonStreamHomePage extends BasePage {

    @FindBy(css = "[class*=styles_hero-carousel-wrapper]")
    public HeroCarousel heroCarousel;

    @FindBy(css = "[class*=placeholder]")
    public List<WebElement> placeholders;

    @FindBy(xpath = "//div[@class='carousel-wrapper' and .//*[text()='Your Watchlist']]")
    public Collection watchlistCollection;
    @FindBy(xpath = "//div[@class='carousel-wrapper' and .//*[text()='Upcoming Movies']]")
    public Collection upcomingMovies;

    @FindBy(css = ".single-promo-card-wrapper")
    public SinglePromoCard singlePromoCard;
    @FindBy(css = ".single-card")
    public SingleShowCard singleShowCard;

    @FindBy(css = "a[href='/browse']")
    public WebElement browseButton;

    public NeonStreamHomePage(WebDriver driver) {
        super(driver);
    }
}
