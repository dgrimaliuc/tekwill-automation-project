package lilia_toma.Petstore;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetstorePage extends BasePage {
    @FindBy (css = "[data-t=location-section]")
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




    public PetstorePage(WebDriver driver) {
        super(driver);
    }
}
