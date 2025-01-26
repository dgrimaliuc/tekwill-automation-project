package Lilia_Rosca.poms;

import example.ui.petstore.components.Pet;
import helpers.BasePage;
import helpers.customElements.Components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(xpath = "//*[@data-t='single-pet']")
    public Components<Pet> pets; //public List<WebElement> pets; - modificat dupa ce a fost creat fisierul cu componente

    public LR_petStorePage(WebDriver driver) {
        super(driver);
    }

}
