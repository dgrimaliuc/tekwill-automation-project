package Leas_Liudmila.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TheGameSection extends Component {
    @FindBy(xpath = ".//h2")
    public WebElement title;

    @FindBy(xpath = ".//p[1]")
    public WebElement webSocketMsg;

    @FindBy(xpath = ".//p[2]")
    public WebElement petsInInfo;

    @FindBy(xpath = ".//p[3]")
    public WebElement adoptionsInInfo;

    public TheGameSection(WebElement parent){
        super(parent);
    }

}
