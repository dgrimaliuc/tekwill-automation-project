package IonErm;

import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EGorin_TestUI {

    ChromeDriver driver = null;

    @Before
    public void before() {
        driver = new ChromeDriverProvider(OS.WINDOWS).getDriver();
    }

    @Given("Open the PetStore")
    public void open_the_pet_store() {
        driver.get("https://petstore-eb41f.web.app/");
    }

    @Then("I see {string} in location input")
    public void i_see_in_location_input(String string) {
        var input = driver.findElement(By.xpath("//div[@class='flex']//input[@value='Plett']"));
        String locationText = input.getAttribute("value");
        assertThat(locationText, equalTo(string));
    }

    @Then("I see {string} Pet section title")
    public void i_see_pet_section_title(String string) {
        var title = driver.findElement(By.xpath("//div[@data-t='pets-section']//h2"));
        String titleText = title.getText();
        assertThat(titleText, equalTo("Pets in " + string));
    }

    @Then("I see {string} in Adoptions Section title")
    public void i_see_in_adoptions_section_title(String string) {
        var adoption = driver.findElement(By.xpath("//div[@data-t='adoptions-section']//h2[text()='Adoptions in Plett']"));
        String adoptionText = adoption.getText();
        assertThat(adoptionText, equalTo("Adoptions in " + string));
    }

    @Then("I click open new tab")
    public void iClickOpenNewTab() {
        var newWindow = driver.findElement(By.xpath("//div[@class='flex']//button[@data-t='open-in-new-tab']"));
        newWindow.click();
    }


    @Given("Change location on {string}")
    public void change_location_on(String string) {
        var location = driver.findElement(By.xpath("//div[@class='flex']//input[@id='location-input']"));
        location.clear();
        location.sendKeys(string);
    }

    @Then("Add pet in your town")
    public void addPet() {
        var addPet = driver.findElement(By.xpath("//div[@class='flex']//button[@data-t='change-location']"));
        addPet.click();
    }

    @Then("Choose {string}")
    public void chooseCheckbox(String string) {
        var petCheckbox = driver.findElement(By.xpath("//div[text()='Kiki']"));
        petCheckbox.click();
    }


    @Then("Adopt the selected pet")
    public void adoptPet() {
        var adopt = driver.findElement(By.xpath("//button[@data-t='adopt-button']"));
        adopt.click();
    }

    @Then("Approve your pet \uD83E\uDDAE")
    public void approvePet() {
        var petName = driver.findElement(By.xpath("//div[@data-t='single-adoption']//div[text()='Kiki']"));
        String petText = petName.getText();
    }

    //Homework
    @Given("Open the PetStore in Chisinau")
    public void open_the_pet_store_in_chisinau() {
        driver.get("https://petstore-eb41f.web.app/?location=Chisinau");
    }

    @Then("Verify the Pets in Chisinau: 5")
    public void verify_the_pets_in_chisinau() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean isTextCorrect = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//p[@data-t='pets-count']//span"),
                "Pets in Chisinau: 5"
        ));
        assertThat("Text did not match after waiting", isTextCorrect, equalTo(true));
    }

    @Then("Verify the Adoptions in Chisinau: 2")
    public void verify_the_adoptions_in_chisinau() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean isTextCorrect = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//p[@data-t='adoptions-count']//span"),
                "Adoptions in Chisinau: 2"
        ));
        assertThat("Text did not match after waiting", isTextCorrect, equalTo(true));
    }

}

















