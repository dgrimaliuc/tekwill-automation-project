package IgorTabirta.UI.Neon.Page;

import IgorTabirta.UI.Neon.Component.HeroCarusel;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class NeonPage extends BasePage {

    @FindBy(css = ".hero-carousel-wrapper")
    public HeroCarusel cards;

    public NeonPage(WebDriver driver) {
        super(driver);
    }
}
