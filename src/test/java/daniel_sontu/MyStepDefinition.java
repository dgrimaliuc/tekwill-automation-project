package daniel_sontu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

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



    @When("i substract the number")
    public void iSubstractTheNumber() {
        result = a-b;
    }

    String string1;
    String string2;
    String concatenatedResult;

    @Given("I have two strings {string} and {string}")
    public void iHaveTwoStringsAnd(String arg0, String arg1) {
        string1 = arg0;
        string2 = arg1;
    }

    @When("I concatenate the strings")
    public void iConcatenateTheStrings() {
        concatenatedResult = string1 + string2;
    }

    @Then("the result should be {string}")
    public void teRsultShouldBe(String arg0) {
        MatcherAssert.assertThat(concatenatedResult, CoreMatchers.equalTo(arg0));
    }

    @When("I subtract the number")
    public void iSubtractTheNumber() {
        result = a-b;
    }

    @Given("I have a string {string}")
    public void iHaveOneString(String input){
        string1 = input;
    }

    @When("i check the length of the string")
    public void iCheckTheLengthOfTheString() {
        result = string1.length();
    }
    @Then("the length should be {int}")
        public void theLengthShouldNe(int expectedLength){
            MatcherAssert.assertThat(result, CoreMatchers.equalTo(expectedLength));
        }

    }