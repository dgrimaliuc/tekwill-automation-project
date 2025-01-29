package IonErm.poms;

import IonErm.components.Adoptions;
import IonErm.components.Pet;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IonErm_PetStorePage extends BasePage {

    @FindBy(id = "location-input")
    public WebElement locationInput;

    @FindBy(xpath = "//div[@data-t='pets-section']//h2")
    public WebElement sectionTitle;

    @FindBy(xpath = "//div[@data-t='adoptions-section']//h2")
    public WebElement adoptionsSection;

    @FindBy(xpath = "//div[@class='flex']//button[@data-t='open-in-new-tab']")
    public WebElement clickOpenNewTabBtn;

    @FindBy(xpath = "//div[@class='flex']//button[@data-t='change-location']")
    public WebElement clickChangeLocationBtn;

    @FindBy(xpath = "//p[@data-t='pets-count']")
    public WebElement petsText;

    @FindBy(xpath = "//p[@data-t='adoptions-count']")
    public WebElement adoptionText;

    @FindBy(xpath = "//tr[@data-t='single-pet']")
    public WebElement petsCount;

    @FindBy(xpath = "//div[@data-t='single-adoption']")
    public WebElement adoptionsCount;

    @FindBy(xpath = "//button[@data-t='add-pet-button']")
    public WebElement clickAddPetsBtn;

    @FindBy(xpath = "//button[@data-t='adopt-button']")
    public WebElement clickAdoptSelectedPetsBtn;

    @FindBy(xpath = "//*[@data-t='pet-name-input']")
    public WebElement petNameInput;

    @FindBy(xpath = "//*[@id='pets-table']//div[contains(@class, 'items-center')]")
    public WebElement emptyPetSection;

    @FindBy(xpath = "//button[@data-t='deselect-button']")
    public WebElement clickDiselectBtn;

    @FindBy(xpath = "//tr[@data-t='single-pet']")
    public Components<Pet> pets;

    @FindBy(xpath = "//*[@data-t='single-adoption']")
    public Components<Adoptions> adoptions;

    public IonErm_PetStorePage(WebDriver driver) {
        super(driver);
    }
}
