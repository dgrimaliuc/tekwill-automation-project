package chiril_bortnicov.ui.neonStream.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCarousel extends Component {

    @FindBy(css = "[class*=placeholder]")
    public WebElement placeholder;

    @FindBy(css = "[class*=hero-carousel-arrows]:nth-child(1):not([class*=placeholder])")
    public WebElement leftArrow;

    @FindBy(css = "[class*=arrow-right]")
    public WebElement rightArrow;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCard> cards;

    @FindBy(css = "[id*=hero-tab]:not([class*=placeholder])")
    public Components<Tab> tabs;

    public int findHeroCard(String type) {
        for (int i = 0; i < cards.size(); i++) {
            tabs.get(i).click();
            if (cards.get(i).title.getAttribute("href").contains(type)) {
                return i;
            }
        }
        return 0;
    }

    public HeroCarousel(WebElement parent) {
        super(parent);
    }
}
