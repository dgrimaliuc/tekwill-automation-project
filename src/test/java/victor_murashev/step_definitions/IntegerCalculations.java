package victor_murashev.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntegerCalculations {
    private int number1;
    private int number2;
    private int result;

    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers_and(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    @When("I add the numbers")
    public void i_add_the_numbers() {
        result = number1 + number2;
    }

    @Then("the result should be {int}")
    public void the_result_should_be(int expectedResult) {
        assertThat("The result of the addition is incorrect.", result, equalTo(expectedResult));
    }
}
