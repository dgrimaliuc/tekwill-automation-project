package Magda_Petrachi.PetStore;

import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class petStorePage extends BasePage {

    @FindBy(css = "[data-t=change-location]")
    public WebElement ChangeLocationButton;

    @FindBy(css = "[data-t=open-in-new-tab]")
    public WebElement OpenNewTabButton;

//    @@FindBy(xpath = "//div[contains(@class, 'text-2xl') and text()='Pets in Plett']")
//    public Component<>


    @FindBy(xpath = "//div[contains(@class, 'text-2xl') and contains(@class, 'ml-4') and text()='Adoptions in Plett']")
    public WebElement adoptionPetInLocation;

    public petStorePage(WebDriver driver) {
        super(driver);
    }
}
