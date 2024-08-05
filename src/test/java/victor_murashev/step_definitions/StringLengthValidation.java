package victor_murashev.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringLengthValidation {
    private String myString;
    private int actualLength;

    @Given("I have a string {string}")
    public void i_have_a_string(String string) {
        myString = string;
    }

    @When("I check the length of the string")
    public void i_check_the_length_of_the_string() {
        actualLength = myString.length();
    }

    @Then("the length should be {string}")
    public void the_length_should_be(String expectedLengthString) {
        int expectedLength = Integer.parseInt(expectedLengthString);

        // Use Hamcrest assertion
        assertThat("The length of the string is incorrect.", actualLength, equalTo(expectedLength));
    }
}
