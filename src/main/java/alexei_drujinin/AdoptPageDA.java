package alexei_drujinin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AdoptPageDA {

    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement locationInput;
    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]//h2")
    public WebElement petsInTitle;
    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]/h2")
    public WebElement adoptionsInTitle;
    @FindBy(xpath = "//div[@class='p-8']/p[2]")
    public WebElement numberOfPetsInfo;
    @FindBy(xpath = "//div[@class='p-8']/p[3]")
    public WebElement adoptionsOfPetsInfo;

    public AdoptPageDA(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
    }
}
