package Lilia_Rosca.components.LR_NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_Carousel extends Component {
// 07.02

    @FindBy(css = ".carousel-title")
    public WebElement title;
    // arrows left
    //right arrow
    // components Browse card


    public LR_Carousel(WebElement parent) {
        super(parent);
    }
}
