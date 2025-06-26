package denis_grimaliuc;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

public class MyStepDefinition {

    int a, b, result;

    String aString, bString, resultString;


    @Given("I have two numbers {int} and {int}")
    public void iHaveTwoNumbersAnd(int arg0, int arg1) {
        a = arg0;
        b = arg1;
        System.out.println("I have two numbers " + a + " and " + b);
    }


    @When("I add the numbers")
    public void iAddTheNumbers() {
        result = a + b;
        System.out.println("I add the numbers, result is " + result);
    }


    @When("I subtract the numbers")
    public void iSubtractTheNumbers() {
        result = a - b;
    }


    @Then("the result should be {int}")
    public void theResultShouldBe(int arg0) {
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(arg0));
    }

    @Given("I have two strings {string} and {string}")
    public void iHaveTwoStringsAnd(String arg0, String arg1) {
        aString = arg0;
        bString = arg1;

    }

    @When("I concatenate the strings")
    public void iConcatenateTheStrings() {
        resultString = aString + bString;
    }

    @Then("the result should be {string}")
    public void theResultShouldBe(String arg0) {
        MatcherAssert.assertThat(resultString, CoreMatchers.equalTo(arg0));
    }
}
