package Magda_Petrachi.NeonStream;

import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NeonStreamHeroCard extends Components {

    @FindBy(css = "a[class*=styles_title]")
    public WebElement title;

    @FindBy(css = "div[class*=styles_description]")
    public WebElement description;

    @FindBy(css = "[class*=styles_hero-card-picture]")
    public WebElement picture;

    @FindBy(css = "[class*=watch-now-btn]")
    public WebElement watchNow;

    @FindBy(css = "[data-t=not-in-watchlist]")
    public WebElement addToWatchlist;

    @FindBy(css = "[data-t=in-watchlist]")
    public WebElement removeFromWatchlist;


    public NeonStreamHeroCard(WebElement parent) {
        super((List<?>) parent);
    }
}