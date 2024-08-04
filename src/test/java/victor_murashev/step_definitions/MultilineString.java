package victor_murashev.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MultilineString {
    private String paragraph;
    private int length;

    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String paragraph) {
        this.paragraph = paragraph;
    }

    @When("I count the length of the string")
    public void i_count_the_length_of_the_string() {
        length = paragraph.length();
    }

    @Then("the length should be {int}")
    public void the_length_should_be(int expectedLength) {
        assertThat("The length of the string is incorrect.", length, equalTo(expectedLength));
    }
}
