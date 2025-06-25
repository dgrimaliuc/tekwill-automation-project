package daniel_sontu;

import org.hamcrest.MatcherAssert;
import org.hamcrest.CoreMatchers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class MyStepDefinition {

    int a, b, result;

    @Given("I have two numbers {int} and {int}")
    public void iHaveTwoNumbersAnd(int arg0, int arg1) {
        a = arg0;
        b = arg1;
    }

    @When("I add the numbers")
    public void iAddTheNumbers() {
        result = a + b;
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(int arg0) {
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(arg0));
    }
}