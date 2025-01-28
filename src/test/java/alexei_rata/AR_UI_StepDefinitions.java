package alexei_rata;

import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AR_UI_StepDefinitions {

    ChromeDriver driver = null;

    @Before
    public void before() {
        driver = new ChromeDriverProvider(OS.WINDOWS).getDriver();
    }

    @Given("Open PetStore")
    public void open_pet_store() {
        driver.get("https://petstore-eb41f.web.app/");

    }

    @Then("I see {string} in location input")
    public void i_see_in_location_input(String string) {
        var input = driver.findElement(By.xpath("//div[@data-t='location-section']//input[@id='location-input']"));
        String locationText = input.getAttribute("value");
        assertThat(locationText, equalTo(string));
    }

    @Then("I see {string} in Pets Section title")
    public void i_see_in_pets_section_title(String string) {
        var title = driver.findElement(By.xpath("//div[@data-t='pets-section']/h2"));
        String titleText = title.getText();
        assertThat(titleText, containsString(string));
    }

    @Then("I see {string} in Adoption Section title")
    public void i_see_in_adoption_section_title(String string) {
        var title = driver.findElement(By.xpath("//div[@data-t='adoptions-section']/h2"));
        String titleText = title.getText();
        assertThat(titleText, equalTo("Adoptions in " + string));
    }

}
