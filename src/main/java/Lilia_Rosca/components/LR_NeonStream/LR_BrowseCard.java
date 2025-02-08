package Lilia_Rosca.components.LR_NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_BrowseCard extends Component {
// 07.02
    @FindBy(css = "img[class*=browse-card-poster]")
    public WebElement poster;

    @FindBy(css = "[class*=browse-card_browse-card-info]")
    public WebElement title;


    public LR_BrowseCard(WebElement parent) {super(parent);}
}