package cristina_savrin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdoptPetsPage {

    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement locationInput;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]/h2")
    public WebElement adoptsTitle;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]//h2")
    public WebElement petsTitle;

    @FindBy(xpath = "//div[@class='p-8']/h2")
    public WebElement gameTitle;

    @FindBy(xpath = "//div[@class='p-8']/p[1]")
    public WebElement websocket;

    @FindBy(xpath = "//div[@class='p-8']/p[2]")
    public WebElement petsInInfo;

    @FindBy(xpath = "//div[@class='p-8']/p[3]")
    public WebElement adoptsInfo;

    @FindBy(xpath = "//div[@id='root']/div/div[3]//input[@type='text']")
    public WebElement petNameInput;

    @FindBy(xpath = "//button[text()=' Add Rescue']")
    public WebElement addPetBtn;

    @FindBy(xpath = "//table/tbody/tr[1]")
    public WebElement lastAddedPetRow;

    @FindBy(xpath = "//table/tbody/tr[1]//td[1]/div/div/div")
    public WebElement lastAddedPetName;

    @FindBy(xpath = "//tbody/tr[not(@class)]/td")
    public List<WebElement> noPetsText;

    @FindBy(xpath = "//button[text()=' Adopt selected pets']")
    public WebElement adoptBtn;

    @FindBy(xpath = "//button[text()='Deselect']")
    public WebElement deselectBtn;

    @FindBy(xpath = "//button[normalize-space(text())='Change location']")
    public WebElement changeLocationBtn;

    @FindBy(xpath = "//button[normalize-space(text())='Open in new Tab']")
    public WebElement openNewTabBtn;

    private WebDriver driver;
    private WebDriverWait wait;

    public AdoptPetsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
}