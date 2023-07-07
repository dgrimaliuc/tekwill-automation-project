package denis_grimaliuc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdoptPage {

    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement locationInput;

    @FindBy(xpath = "//button[normalize-space(text())='Change location']")
    public WebElement changeLocationBtn;

    @FindBy(xpath = "//button[normalize-space(text())='Open in new Tab']")
    public WebElement openNewTabBtn;

    @FindBy(xpath = "//div[@class='p-8']/p[2]")
    public WebElement petsInInfo;

    @FindBy(xpath = "//div[@class='p-8']/p[3]")
    public WebElement adoptsInfo;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]/h2")
    public WebElement adoptsTitle;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]//h2")
    public WebElement petsTitle;

    @FindBy(xpath = "//button[text()=' Add Rescue']")
    public WebElement addPetBtn;

    @FindBy(xpath = "//div[@id='root']/div/div[3]//input[@type='text']")
    public WebElement petNameInput;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> pets;


    private WebDriver driver;
    private WebDriverWait wait;

    public AdoptPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void waitForPageToLoad() {
        // TODO to implement
    }

}
