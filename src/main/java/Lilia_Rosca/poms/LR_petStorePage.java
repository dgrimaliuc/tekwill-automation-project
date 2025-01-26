package Lilia_Rosca.poms;

import helpers.BasePage;
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

    public LR_petStorePage(WebDriver driver) {
        super(driver);
    }

}
