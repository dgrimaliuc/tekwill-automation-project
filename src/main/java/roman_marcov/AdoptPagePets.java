package roman_marcov;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdoptPagePets {

    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement locationInput;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]//h2")
    public WebElement petsTitle;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]/h2")
    public WebElement adoptationsTitle;

    @FindBy(xpath = "//div[@class='p-8']/p[2]")
    public WebElement petsInInfo;

    @FindBy(xpath = "//div[@class='p-8']/p[3]")
    public WebElement adoptsInfo;

    @FindBy(xpath = "//div[@id='root']/div/div[3]//input[@type='text']")
    public WebElement petNameInput;

    @FindBy(xpath = "//table/tbody/tr[1]")
    public WebElement firstAddedPet;

    @FindBy(xpath = "//div[@id='root']/div/div[3]//input[@type='text']")
    public WebElement nameInput;

    @FindBy(xpath = "//button[text()=' Add Rescue']")
    public WebElement buttonAddRescue;

    @FindBy(xpath = "//table/tbody/tr[2]")
    public WebElement secondAddedPet;


    public AdoptPagePets(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
}
