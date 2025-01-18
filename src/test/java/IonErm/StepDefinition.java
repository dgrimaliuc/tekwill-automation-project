package IonErm;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinition {

    Map<String, Object> context = new HashMap<>();

    @Given("I have a string {string}")
    public void iHaveAString(String string) {
        context.put("myString", string);
    }

    @When("I check if it contains {string}")
    public void iCheckIfItContains(String subString) {
        String myString = (String) context.get("myString");
        boolean contains = myString.contains(subString);
        context.put("contains", contains);
    }

    @Then("I should return true")
    public void iShouldReturnTrue() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    @When("I reverse the string")
    public void i_reverse_the_string() {
        String myString = (String) context.get("myString");
        String reversedString = new StringBuilder(myString).reverse().toString();
        context.put("reversed_string", reversedString);
    }

    @Then("The reversed string should be {string}")
    public void the_reversed_string_should_be(String string) {
        String reversedString = (String) context.get("reversed_string");
        assertThat(reversedString, equalTo(string));
    }

    @When("I check the length of the string")
    public void i_check_the_length_of_the_string() {
        String myString = (String) context.get("myString");
        int stringLength = myString.length();
        context.put("stringLength", stringLength);

    }

    @Then("the length should be {int}")
    public void the_length_should_be(Integer expectedLength) {
        Integer actualLength = (Integer) context.get("stringLength");
        assertThat(actualLength, equalTo(expectedLength));
    }

}
