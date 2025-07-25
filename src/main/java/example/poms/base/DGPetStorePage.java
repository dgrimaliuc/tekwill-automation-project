package example.poms.base;

import example.components.petStore.Adoption;
import example.components.petStore.Pet;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DGPetStorePage extends BasePage {

    public static By allPets = By.xpath("//tr[@data-t='single-pet']");
    public static By allAdoptions = By.xpath("//div[@data-t='single-adoption']");

    @FindBy(id = "location-input")
    public WebElement locationInput;

    @FindBy(xpath = "//div[@data-t='pets-section']/h2")
    public WebElement petSectionTitle;
    @FindBy(xpath = "//div[@data-t='adoptions-section']/h2")
    public WebElement adoptSectionTitle;
    @FindBy(xpath = "//button[@data-t='change-location']")
    public WebElement changeLocationBtn;
    @FindBy(xpath = "//button[@data-t='open-in-new-tab']")
    public WebElement openInNewTabBtn;
    @FindBy(xpath = "//p[@data-t='pets-count']")
    public WebElement petsCount;
    @FindBy(xpath = "//p[@data-t='adoptions-count']")
    public WebElement adoptionsCount;
    @FindBy(xpath = "//*[@data-t='pet-name-input']")
    public WebElement petNameInput;
    @FindBy(xpath = "//button[@data-t='add-pet-button']")
    public WebElement addPetButton;
    @FindBy(xpath = "//button[@data-t='adopt-button']")
    public WebElement adoptSelectedButton;

    @FindBy(xpath = "//*[@data-t='single-pet']")
    public Components<Pet> pets;

    @FindBy(xpath = "//*[@data-t='single-adoption']")
    public Components<Adoption> adoptions;

    @FindBy(xpath = "//*[@id='pets-table']//div[contains(@class,'items-center')]")
    public WebElement emptyPetsMessage;
    @FindBy(xpath = "//button[@data-t='deselect-button']")
    public WebElement deselectButton;

    public DGPetStorePage(WebDriver driver) {
        super(driver);
    }

}
