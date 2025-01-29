package Lilia_Rosca.poms;

import Lilia_Rosca.components.LR_pets.LR_adoption;
import Lilia_Rosca.components.LR_pets.LR_pet;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_petStorePage extends BasePage {

    public static By allPets = By.xpath("//tr[@data-t='single-pet']");
    public static By allAdoptions = By.xpath("//div[@data-t='single-adoption']");

//  @FindBy(xpath = "//input[@id='location-input']") - var 1
    @FindBy(id = "location-input")
    public WebElement locationInput;

    @FindBy(xpath = "//div[@data-t='pets-section']/h2")
    public WebElement petSectionTitle;

    @FindBy(xpath = "//div[@data-t='adoptions-section']/h2")
    public WebElement adoptionSectionTitle;

    @FindBy(xpath = "//button [@data-t = 'change-location']")
    public  WebElement buttonChangeLocation;

    @FindBy(xpath = "//button [@data-t = 'open-in-new-tab']")
    public  WebElement buttonOpenInNewTab;

    @FindBy(xpath = "//p[@data-t ='pets-count']")
    public  WebElement petsCount;

    @FindBy(xpath = "//p[@data-t ='adoptions-count']")
    public  WebElement adoptionCount;

    @FindBy(xpath = "//button [@data-t = 'add-pet-button']")
    public WebElement addPetButton;

    @FindBy(xpath = "//*[@data-t='pet-name-input']")
    public WebElement petNameInput;

    @FindBy(xpath = "//button[@data-t='adopt-button']")
    public WebElement adoptSelectedButton;

    @FindBy(xpath = "//*[@data-t='single-pet']")
    public Components<LR_pet> pets; //public List<WebElement> pets; - modificat dupa ce a fost creat fisierul cu componente

    @FindBy(xpath = "//*[@data-t='single-adoption']")
    public Components<LR_adoption> adoptions;

    @FindBy(xpath =  "//*[@id= 'pets-table']//div[contains(@class, 'items-center')]")
    // sau cu text daca pagina are doar o singura limba - //*[@id= "pets-table"]//div[contains(text(), 'No rows')]
    public WebElement emptyPageMessage;

    @FindBy(xpath = "//button [@data-t = 'deselect-button']")
    public WebElement deselectButton;

    public LR_petStorePage(WebDriver driver) {
        super(driver);
    }

}
