package denis_grimaliuc;

import denis_grimaliuc.components.Adoption;
import denis_grimaliuc.components.Adoptions;
import denis_grimaliuc.components.PetsSection;
import denis_grimaliuc.components.TheGameSection;
import helpers.customElements.Components;
import helpers.customElements.factories.CustomPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdoptPage {

    public static final By FIRST_ROW_IN_TABLE = By.xpath("//table/tbody/tr[1]/td[1]");
    public static final By EMPTY_PET_TABLE_MESSAGE = By.xpath("//div[normalize-space(text())='No rows. Try reset filters']");
    public static final By ADOPT_ROWS = By.xpath("//div[@id='root']/div/div[3]/div[2]//div[@class='mt-2 border-gray-400 border p-4 rounded-md']");
    public static final By ROWS = By.xpath("//table/tbody/tr");

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]//div[@class='mt-2 border-gray-400 border p-4 rounded-md']")
    public Components<Adoption> adoptions;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]//div[@class='mt-2 border-gray-400 border p-4 rounded-md']")
    public Adoptions adopts;

    @FindBy(xpath = "//input[@id='location-input']")
    public WebElement locationInput;

    @FindBy(xpath = "//button[normalize-space(text())='Change location']")
    public WebElement changeLocationBtn;

    @FindBy(xpath = "//div[@class='p-8']")
    public TheGameSection theGame;

    @FindBy(xpath = "//button[normalize-space(text())='Open in new Tab']")
    public WebElement openNewTabBtn;


    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[2]/h2")
    public WebElement adoptsTitle;

    @FindBy(xpath = "//div[@id='root']/div/div[3]/div[1]")
    public PetsSection petsIn;


    public AdoptPage(WebDriver driver) {
        CustomPageFactory.initElements(driver, this, null);
    }

    public String getStatusOfPet(WebElement pet) {
        return pet.findElement(By.xpath("//td[2]")).getText();
    }
}
