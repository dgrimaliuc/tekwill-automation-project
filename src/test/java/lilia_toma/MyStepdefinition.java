package lilia_toma;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

public class MyStepdefinition {
    int a, b, result;

    String aString, bString, resultString;

    String myString;
    int stringLength;

    String argString;

    int numberCheck;
    boolean isEven;

    @Given("I have two numbers {int} and {int}")
    public void iHaveTwoNumbersAnd(int arg0, int arg1) {
        this.a = arg0;
        this.b = arg1;
        System.out.println("I have two numbers " + a + " and " + b);
    }

    @When("I add the numbers")
    public void i_add_the_numbers() {
        this.result = a + b;
        System.out.println("I add the numbers, result is " + result);
    }

//    @Then("the result should be {int}")
//    public void theResultShouldBe(int arg0) {
//        MatcherAssert.assertThat(result, CoreMatchers.equalTo(arg0));
//    }

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

    @Given("I have a string {string}")
    public void i_have_a_string(String inputstring) {
        this.myString = inputstring;
        System.out.println("Given string: \"" + myString + "\"");

    }

    @When("I check the length of the string")
    public void i_check_the_length_of_the_string() {
        this.stringLength = myString.length();
        System.out.println("Checked string length: " + stringLength);

    }

    @Then("the length should be {int}")
    public void the_length_should_be(int expectedLength) {
        MatcherAssert.assertThat(stringLength, CoreMatchers.equalTo(expectedLength));// Write code here that turns the phrase above into concrete actions    throw new cucumber.api.PendingException();}
    }

    @When("I check if the string contains {string}")
    public void iCheckIfTheStringContains(String argString) {
        this.argString = argString;
        System.out.println("Check if the string contains: " + argString);
    }

    @Then("the string should contain {string}")
    public void theStringShouldContain(String containString) {
        MatcherAssert.assertThat(argString, CoreMatchers.equalTo(containString));
    }

    @Given("I have a number {int}")
    public void iHaveANumber(int inputNumber) {
        this.numberCheck = inputNumber;
        System.out.println("Number is: " + numberCheck);
    }

    @When("I check if the number is even")
    public void iCheckIfTheNumberIsEven() {
        this.isEven = (numberCheck % 2 == 0);
        System.out.println("Check if " + numberCheck + " is even. Result: " + isEven);
    }
    @Then("the number should be even")
    public void the_number_should_be_even () {
        MatcherAssert.assertThat(isEven, CoreMatchers.equalTo(true));
    }
}


