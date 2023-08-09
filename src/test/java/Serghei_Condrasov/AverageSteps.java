package Serghei_Condrasov;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class AverageSteps {

    Logger logger = Logger.getLogger(AverageSteps.class);

    Integer a, b, c, result;

    @Given("^I have three numbers \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" SC$")
    public void iHaveThreeNumber(Integer num1, Integer num2, Integer num3) {
        a = num1;
        b = num2;
        c = num3;
        logger.info("Numbers initialised " + a + "," + b + "," + c);
    }

    @When("^I find average of three numbers SC$")
    public void iFindAverageOfThreeNumbersSC() {
        result = (a + b + c) / 3;
        logger.info("Average numbers" + " " + result);
    }

    @Then("^the average should be \"([^\"]*)\" SC$")
    public void theAverageShouldBeSC(Integer expectedResult) {
        logger.info("Validating result");
        assertThat(result, Matchers.equalTo(expectedResult));
    }
}

