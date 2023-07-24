package Leas_Liudmila;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LLAdoptPage {

    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement myLocationInput;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]//h2")
    public WebElement petsInTitle;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]/h2")
    public WebElement adoptationsInTitle;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/h2")
    public WebElement theGameSectionTitle;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/p[1]")
    public WebElement firstRowOfGameSection;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/p[2]")
    public WebElement secondRowOfGameSection;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/p[3]")
    public WebElement thirdRowOfGameSection;

    @FindBy(xpath = "//div[@id='root']/div/div[3]//input[@type='text']")
    public WebElement nameInput;

    @FindBy(xpath = "//button[text()=' Add Rescue']")
    public WebElement buttonAddRescue;

    @FindBy(xpath = "//table/tbody/tr[1]")
    public WebElement firstAddedPet;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> addedPets;

    @FindBy(xpath = "//table/tbody/tr[.//div[not(contains(text(),'No rows. Try reset filters'))]]")
    public List<WebElement> pets;

    @FindBy(xpath = "//table/tbody/tr/td/span")
    public WebElement petStatus;

    @FindBy(xpath = "//tbody/tr[not(@class)]/td")
    public List<WebElement> noRowsText;

    @FindBy(xpath = "//button[text()=' Adopt selected pets']")
    public WebElement adoptSelPetsBtnActive;

    @FindBy(xpath = "//button[@disabled and contains (text(), 'Deselect')]")
    public List<WebElement> deselectBtnInactive;

    @FindBy(xpath = "//button[@disabled and .//text() [contains (., 'Adopt selected pets')]]")
    public List<WebElement> adoptSelPetsBtnInactive;




    private WebDriver driver;

    private WebDriverWait wait;

    public LLAdoptPage (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }



}
