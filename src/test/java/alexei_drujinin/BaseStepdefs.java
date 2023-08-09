package alexei_drujinin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;


public class BaseStepdefs {
    private static final Logger logger = Logger.getLogger(BaseStepdefs.class);
    int a;
    int b;
    int c;
    int result;

    @Given("I have three numbers {string}, {string} and {string}")
    public void iHaveThreeNumbers(String firstNum, String secondNum, String thirdNum) {
        a = Integer.parseInt(firstNum);
        b = Integer.parseInt(secondNum);
        c = Integer.parseInt(thirdNum);
        logger.info("First number = " + firstNum);
        logger.info("Second number = " + secondNum);
        logger.info("Third number = " + thirdNum);
    }

    @When("I find average of three numbers")
    public void findAverage() {
        result = (a + b + c) / 3;
        logger.info("Average of 3 numbers is = " + result);
    }

    @Then("the average should be {string}")
    public void theAverageShouldBe(String expectedValue) {

        Assertions.assertEquals(Integer.parseInt(expectedValue), result, "Expected Value is not the same as actual");
    }
}

