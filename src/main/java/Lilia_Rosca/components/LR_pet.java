package Lilia_Rosca.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_pet extends Component {
    @FindBy(xpath = ".//div[@data-t='pet-name']") // . in xpath - pentru ca fiecare companent sa aiba atributul sau - numele, statut, etc
    public WebElement name;

    @FindBy(xpath = ".//*[@data-t='pet-status']")
    public WebElement status;

    @FindBy(xpath = ".//*[@data-t='checked-icon']")
    public WebElement checkedIcon;

    public LR_pet(WebElement parent) {
        super(parent);
    }
}
