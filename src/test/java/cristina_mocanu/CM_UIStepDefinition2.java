package cristina_mocanu;

import example.data.enums.OS;
import helpers.customElements.Component;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CM_UIStepDefinition2 {
    ChromeDriver driver = null;

@Before
public void before (){
    driver = new ChromeDriverProvider(OS.MAC).getDriver();

}


@Given("Open PetStore")
    public void open_petstore(){
    driver.get("https://petstore-eb41f.web.app/");
}

@Then("I see {string} in location input")
    public void i_see_in_location_input (String string){
    String locationText;
    var input = driver.findElement(By.xpath("//input[@id='location-input']"));
 String locationText = input.getAttribute("value");
 assertThat(locationText, equalTo(string));


}

@Then("I see {string} in Info Section title")
    public void i_see_in_info_section_title (String string){
var title = driver.findElement(By.xpath("//div[@data-t='pets-count']/h2"));
String countText = title.getText();
assertThat(countText, equalTo("Pets in Chisinau:" + string));
    }

}@Then("I see {string} in Info Section title")
public void i_see_info_section_title (String string){
    Component driver;
    var title = driver.findElement(By.xpath("//div[@data-t='adoptions-count']/h2"));
    String cauntText = title.getText();
    assertThat(cauntText, equalTo("Adoptions in Chisinau:" + string));
}

}
