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

    public AdoptPagePets(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
}
