package IgorTabirta.UI.Neon.Component;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroCarousel extends Component {

    @FindBy(css = "[class*=placeholder]")
    public WebElement placeHolder;

    @FindBy(css = "[class*=arrow_hero-carousel-arrows]:nth-child(1):not([class*=placeholder])")
    public WebElement leftArrow;

    @FindBy(css = "[class*=arrow_hero-carousel-arrows]:nth-child(3):not([class*=placeholder])")
    public WebElement rightArrow;

    @FindBy(css = "[class*=hero-card-container]")
    public Components<HeroCard> cards;

    @FindBy(css = "[id*=hero-tab]")
    public Components<Tab> tabs;

    public int findHeroCard(String type) {
        for (int i = 0; i < cards.size(); i++) {
            tabs.get(i).click();
            if (cards.get(i).title.getAttribute("href").contains(type.toLowerCase())) {
                return i;
            }
        }
        return 0;
    }


    public HeroCarousel(WebElement parent) {
        super(parent);
    }
}
