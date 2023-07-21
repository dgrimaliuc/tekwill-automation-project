package denis_grimaliuc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdoptPage {

    public static final By FIRST_ROW_IN_TABLE = By.xpath("//table/tbody/tr[1]/td[1]");
    public static final By EMPTY_PET_TABLE_MESSAGE = By.xpath("//div[normalize-space(text())='No rows. Try reset filters']");
    public static final By ADOPT_ROWS = By.xpath("//div[@id='root']/div/div[3]/div[2]//div[@class='mt-2 border-gray-400 border p-4 rounded-md']");
    public static final By ROWS = By.xpath("//table/tbody/tr");
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement locationInput;

    @FindBy(xpath = "//button[normalize-space(text())='Change location']")
    public WebElement changeLocationBtn;

    @FindBy(xpath = "//button[normalize-space(text())='Open in new Tab']")
    public WebElement openNewTabBtn;

    @FindBy(xpath = "//div[@class='p-8']/p[2]")
    public WebElement petsInInfo;

    @FindBy(xpath = "//div[@id='root']/div/div[2]/p[2]")
    public WebElement theGamePetsInInfo;

    @FindBy(xpath = "//div[@class='p-8']/p[3]")
    public WebElement adoptsInfo;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]/h2")
    public WebElement adoptsTitle;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]//h2")
    public WebElement petsTitle;

    @FindBy(xpath = "//button[text()=' Add Rescue']")
    public WebElement addPetBtn;

    @FindBy(xpath = "//button[text()=' Adopt selected pets']")
    public WebElement adoptButton;

    @FindBy(xpath = "//div[@id='root']/div/div[3]//input[@type='text']")
    public WebElement petNameInput;

    @FindBy(xpath = "//table/tbody/tr[.//div[not(contains(text(),'No rows. Try reset filters'))]]")
    public List<WebElement> pets;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]//div[@class='mt-2 border-gray-400 border p-4 rounded-md']")
    public List<WebElement> adopts;

    public AdoptPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isDefaultStateMessageDisplayed() {
        return driver.findElements(EMPTY_PET_TABLE_MESSAGE).size() > 0;
    }

    public void waitForPageToLoad() {
        // TODO to implement
    }

}
