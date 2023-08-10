package ana_raetcaia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    private WebDriver driver;

    public AdoptPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
