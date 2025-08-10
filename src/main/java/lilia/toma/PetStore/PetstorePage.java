package lilia.toma.PetStore;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PetstorePage extends BasePage{

    @FindBy(css = "[data-t=location-section]")
    public WebElement location;

    @FindBy (css = "[data-t=info-section]")
    public WebElement theGame;

    @FindBy (css = "[data-t=pets-section]")
    public WebElement pets;

    @FindBy (css = "[data-t=adoptions-section]")
    public WebElement adoptions;

    @FindBy (css = "input[id='location-input']")
    public WebElement locationInput;

    @FindBy (css = "input[value='Plett']")
    public WebElement defaultPlett;

    @FindBy (css = "[data-t=change-location]")
    public WebElement changeLocation;

    @FindBy (css = "[data-t='open-in-new-tab']")
    public WebElement openInNewTabBtn;

    @FindBy (css = "input[value='Chisinau']")
    public WebElement locationChisinau;

    @FindBy (css = "h2.text-2xl.ml-4")
    public WebElement verifyLocation;

    @FindBy (css = "[class='flex items-center']")
    public WebElement petsResult;


    @FindBy (css = "[data-t='adopt-button']")
    public WebElement adoptButton;

    @FindBy (css = "[data-t='pet-name-input']")
    public WebElement petNameImput;

    @FindBy (css = "[data-t='add-pet-button']")
    public WebElement addPetButton;

    @FindBy (css = "[data-t='pet-status']")
    public WebElement petStatus;

    @FindBy (css = "[data-t='pets-count']")
    public WebElement petsCount;

    @FindBy (css = "[data-t='pet-name']")
    public WebElement petName;

    @FindBy (css = "[id='pets-table']")
    public List<WebElement> petsTable;

    @FindBy (css = "div[data-t='checkbox']")
    public WebElement petCheckbox;

    @FindBy (css = "svg[data-t='checked-icon']")
    public WebElement petCheckboxIcon;

    @FindBy (css = "[data-t='deselect-button']")
    public WebElement deselectButton;




    public PetstorePage(WebDriver driver) {
        super(driver);
    }
}