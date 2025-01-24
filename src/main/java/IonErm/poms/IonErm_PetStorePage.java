package IonErm.poms;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public IonErm_PetStorePage(WebDriver driver) {
        super(driver);
    }
}
