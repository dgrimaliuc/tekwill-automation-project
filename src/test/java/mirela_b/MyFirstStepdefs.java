package mirela_b;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;


import static org.hamcrest.MatcherAssert.assertThat;

public class MyFirstStepdefs {
    Logger logger = Logger.getLogger(MyFirstStepdefs.class);
    Integer a;
    Integer b;
    Integer c;
    Integer avg;

    @Given("^I have three numbers \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void defineNumbers(Integer arg0, Integer arg1, Integer arg2) {
        a = arg0;
        b = arg1;
        c = arg2;
        logger.info("Initialized numbers: " + a + " " + b + " " + c);
    }

    @When("I find the average of three numbers")
    public void iFindTheAverageOfThreeNumbers() {
        avg = (a + b + c) / 3;
        logger.info("The average is: " + avg);
    }

    @Then("^The average is \"([^\"]*)\"$")
    public void checkTheAverage(Integer expectedResult) {
        assertThat(avg, Matchers.equalTo(expectedResult));
        logger.info("Check the average : " + expectedResult);
    }
}
