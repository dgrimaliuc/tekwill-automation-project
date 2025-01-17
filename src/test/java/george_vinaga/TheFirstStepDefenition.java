package george_vinaga;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TheFirstStepDefenition {

    Map<String, String> context = new HashMap<>();

    @Given("I have a string {string}")
    public void i_have_a_string(String string) {
        context.put("my_string", string);
    }

    @When("I check if it contains {string}")
    public void iCheckIfItContains(String subString) {
        String myString = context.get("my_string");
        boolean contains = myString.contains(subString);
        context.put("contains123", String.valueOf(contains));
    }

    @Then("It should return true")
    public void itShouldReturnTrue() {
        String contains = context.get("contains123");
        assertThat(contains, equalTo("true"));
    }
}
