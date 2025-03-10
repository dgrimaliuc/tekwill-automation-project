package IuliaNamolovan;

import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IN_UIStepDef {
    ChromeDriver driver = null;

    @Before
    public void before() {
        driver = new ChromeDriverProvider(OS.MAC).getDriver();
    }

    @Given("Open Petstore")
    public void open_petstore() {
        driver.get("https://petstore-eb41f.web.app/");
    }

    @Then("I see {string} in location input")
    public void i_see_in_location_input(String string) {
        var input = driver.findElement(By.xpath("//input[@id='location-input']"));
        String locationText = input.getAttribute("value");
        assertThat(locationText, equalTo(string));
    }

    @Then("I see {string} in Pets Section title")
    public void i_see_in_pets_section_title(String string) {
        var title = driver.findElement(By.xpath("//div[@data-t='pets-section']//h2"));
        String titleText = title.getText();
        assertThat(titleText, equalTo("Pets in " + string));
    }

    @Then("I see {string} in Adoption Section title")
    public void i_see_in_adoption_section_title(String string) {
        var title = driver.findElement(By.xpath("//div[@data-t='adoptions-section']//h2"));
        String titleText = title.getText();
        assertThat(titleText, equalTo("Adoptions in " + string));
    }
    @Given("Open Petstore in {string}")
    public void open_petstore_in(String string) {
        driver.get("https://petstore-eb41f.web.app/?location=Chisinau");
    }
    @Then("I validate the info section")
    public void i_validate_the_info_section() {
        waitFor(3);
        var petsCount = driver.findElement(By.xpath( "//div[@data-t='info-section']//p[@data-t='pets-count']")).getText();
        var adoptionsCount = driver.findElement(By.xpath( "//div[@data-t='info-section']//p[@data-t='adoptions-count']")).getText();
        assertThat(petsCount, equalTo("Pets in Chisinau: 4" ));
        assertThat(adoptionsCount, equalTo("Adoptions in Chisinau: 2" ));
    }
}