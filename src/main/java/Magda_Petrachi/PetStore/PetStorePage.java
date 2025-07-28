package Magda_Petrachi.PetStore;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PetStorePage extends BasePage {

    @FindBy(css = "div[data-t=location-section]")
    public LocationSection locationSection;

    @FindBy(xpath = "//div[@data-t='info-section']//h2[contains(@class, 'text-2xl') and text()=' The game']")
    public InfoSection infoSection;

    @FindBy(xpath = "//div[@data-t='adoptions-section']//h2[contains(@class, 'text-2xl') and text()='Adoptions in']")
    public AdoptionPetSection adoptionPetSection;

    @FindBy(xpath = "//div[@data-t='pets-section']//h2[contains(@class, 'text-2xl')]")
    public PetInSection petInSection;


    public PetStorePage(WebDriver driver) {
        super(driver);
    }
}
