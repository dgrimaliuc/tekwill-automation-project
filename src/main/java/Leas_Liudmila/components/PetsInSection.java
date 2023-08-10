package Leas_Liudmila.components;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PetsInSection extends Component {

    @FindBy(xpath = ".//table/tbody/tr[.//div[not(contains(text(),'No rows. Try reset filters'))]]")
    public List<WebElement> pets;


    @FindBy(xpath = ".//button[contains (text(), 'Deselect')]")
    public WebElement deselectBtnActive;

    @FindBy(xpath = ".//button[.//text() [contains (., 'Adopt selected pets')]]")
    public WebElement adoptSelPetsBtnActive;

    @FindBy(xpath = ".//td/span")
    public List<WebElement> petsStatus;


    @FindBy(xpath = ".//tr/td/div/div/div")
    public List<WebElement> petsName;


    public PetsInSection(WebElement parent) {
        super(parent);
    }
}
