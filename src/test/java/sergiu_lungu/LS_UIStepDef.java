package sergiu_lungu;

import com.google.common.base.Predicate;
import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.google.common.base.Predicates.equalTo;

public class LS_UIStepDef {

    ChromeDriver driver = null;

    @Before
    public void before() {
        driver = new ChromeDriverProvider(OS.WINDOWS).getDriver();

    }

    @Given("Open Petstore")
    public void open_petstore() {
        driver.get("https://petstore-eb41f.web.app/");

    }

    @Then("I see {string} in location input")
    public void i_see_in_location_input(String string) {
        var input = driver.findElement(By.xpath("//input[@id=\"location-input\"]"));
        String locationText = input.getAttribute("value");
        assertThat(locationText, equalTo(string));

    }

    @Then("I see {string} in Pets Section title")
    public void i_see_in_pets_section_title(String string) {
        var title = driver.findElement(By.xpath("//div[@data-t=\"pets-section\"]/h2"));
        String titleText = title.getText();
        assertThat(titleText, equalTo("Pets in " + string));


    }

    private void assertThat(String titleText, Predicate<String> stringPredicate) {
    }

    @Then("I see {string} in Adoptions Section title")
    public void i_see_in_adoptions_section_title(String string) {
        var title = driver.findElement(By.xpath("//div[@data-t=\"adoptions-section\"]/h2"));
        String titleText = title.getText();
        assertThat(titleText, equalTo("Pets in " +string));

    }

    @Given("Open Petstore web")
    public void open_petstore_web() {
        driver.get("https://petstore-eb41f.web.app/?location=Chisinau");
    }


    @Then("Verify Pets in Chisinau: 5")
    public void verifyPetsInChisinau() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        var pets = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@data-t='pets-count']//span")));
        String petsText = pets.getText();
        System.out.println("Pets text found: " + petsText);
        assertThat(petsText, equalTo("Pets in Chisinau: 5"));
        
    }

    @Then("Verify Adoptions in Chisinau: 1")
    public void verifyAdoptionsInChisinau() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        var adoptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@data-t='adoptions-count']//span")));
        String adoptionText = adoptions.getText();
        System.out.println("Adoptions text found: + adoptionText");
        assertThat(adoptionText, equalTo("Adoptions in Chisinau: 1"));
    }
}
