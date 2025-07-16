package Magda_Petrachi.NeonStream;

import example.components.neonStream.HeroCarousel;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NeoStreamPage extends BasePage {

    @FindBy(xpath = "//span[text()='Home']")
    public WebElement Home;


    @FindBy(css = "[class*=styles_hero-carousel-wrapper]")
    public HeroCarousel heroCarousel;

    @FindBy(css = "[class*=placeholder]")
    public List<WebElement> placeholders;

    public NeoStreamPage(WebDriver driver) {
        super(driver);
    }
}
