package victor_murashev.ui.neonStream.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCarousel extends Component {

    @FindBy(css = "[class*=placeholder']")
    public WebElement placeholder;

    //@FindBy(css = "[alt='Scrollleft']")
    @FindBy(xpath = "//div[@role='button'][.//@alt='Scrollleft']")
    public WebElement arrowLeft;

    //@FindBy(css = "[alt='Scrollright']")
    @FindBy(xpath = "//div[@role='button'][.//@alt='Scrollright']")
    public WebElement arrowRight;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCard> cardContainers;

    @FindBy(css = "[id*='hero-tab']:not([class*=placeholder])")
    public Components<Tab> tabs;

    public int findHeroCard(String type) {
        for (int i = 0; i < cardContainers.size(); i++) {
            tabs.get(i).click();
            if (cardContainers.get(i).cardTitle.getAttribute("href").contains(type)) {
                return i;
            }

        }
        return 0;
    }

    public HeroCarousel(WebElement parent) {
        super(parent);
    }
}
