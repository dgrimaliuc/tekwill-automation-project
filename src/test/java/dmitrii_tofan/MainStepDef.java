package dmitrii_tofan;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class MainStepDef {
    Logger logger = Logger.getLogger(MainStepDef.class);

    Integer a, b, c, result;

    @Given("^three numbers \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void threeNumbersAnd(Integer num1, Integer num2, Integer num3) {
        a = num1;
        b = num2;
        c = num3;
        logger.info("Numbers initialized: " + a + ", " + b + ", " + c);
    }

    @When("^find average of three numbers$")
    public void findAverageOfThreeNumbers() {
        result = (a + b + c) / 3;
        logger.info("Average result calculating: " + result);
    }

    @Then("^the average should be  \"([^\"]*)\"$")
    public void theAverageShouldBe(Integer expectedResult)  {
        logger.info("Validating result");
        assertThat(result, Matchers.equalTo(expectedResult));
    }

}

