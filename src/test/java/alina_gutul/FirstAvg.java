package alina_gutul;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class FirstAvg {

    private int num1;
    private int num2;
    private int num3;
    private int average;

    @Given("Given three numbers {string}, {string} and {string} AG")
    public void given_three_numbersAG(String strNum1, String strNum2, String strNum3) {
        num1 = Integer.parseInt(strNum1);
        num2 = Integer.parseInt(strNum2);
        num3 = Integer.parseInt(strNum3);
        throw new io.cucumber.java.PendingException();
    }

    @When("Find average of three numbers")
    public void find_average_of_three_numbers() {
        average = (num1 + num2 + num3) / 3;
        throw new io.cucumber.java.PendingException();
    }

    @Then("Average should be {string}")
    public void average_should_be(String expectedAverage) {
        int expected = Integer.parseInt(expectedAverage);
        if (average == expected) {
            System.out.println("Average is correct: " + average);
        } else {
            System.out.println("Average is incorrect. Expected: " + expected + ", Actual: " + average);
        }
        throw new io.cucumber.java.PendingException();
    }
}
