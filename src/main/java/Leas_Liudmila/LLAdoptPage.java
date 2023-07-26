package Leas_Liudmila;

import Leas_Liudmila.components.AdoptionsInSection;
import Leas_Liudmila.components.PetsInSection;
import helpers.customElements.factories.CustomPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LLAdoptPage {

    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement myLocationInput;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]//h2")
    public WebElement petsInTitle;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]/h2")
    public WebElement adoptionsInTitle;

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

    @FindBy(xpath = "//table/tbody/tr[2]")
    public WebElement secondAddedPet;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> addedPets;

    @FindBy(xpath = "//table/tbody/tr[.//div[not(contains(text(),'No rows. Try reset filters'))]]")
    public List<WebElement> pets;

    @FindBy(xpath = "//tbody/tr[not(@class)]/td")
    public List<WebElement> noRowsText;

    @FindBy(xpath = "//button[text()=' Adopt selected pets']")
    public WebElement adoptSelPetsBtnActive;

    @FindBy(xpath = "//button[@disabled and contains (text(), 'Deselect')]")
    public List<WebElement> deselectBtnInactive;

    @FindBy(xpath = "//button[@disabled and .//text() [contains (., 'Adopt selected pets')]]")
    public List<WebElement> adoptSelPetsBtnInactive;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]")
    public PetsInSection petsIn;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]")
    public AdoptionsInSection adoptionsIn;

    public String getStatusOfPet(WebElement pet) {
        return pet.findElement(By.xpath("//td[2]")).getText();
    }

    public LLAdoptPage(WebDriver driver) {
        CustomPageFactory.initElements(driver, this, null);
    }


}
