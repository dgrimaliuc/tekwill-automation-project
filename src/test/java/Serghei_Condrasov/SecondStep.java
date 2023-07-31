package Serghei_Condrasov;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class SecondStep {

    Logger logger = Logger.getLogger(SecondStep.class);

    Map<String, Object> context = new HashMap<>();

    @Given("^I have a string \"([^\"]*)\" SC$")
    public void iHaveAString(String arg0) {
        logger.info("I have a string " + arg0);
        context.put("my_String", arg0);
    }

    @When("I check the length of the string SC")
    public void iCheckTheLengthOfTheStringSC() {
        String myString = (String) context.get("my_String");
        logger.info("Length of the string" + " - " + myString.length());
        context.put("lengthString", myString.length());
    }

    @Then("^the length should be \"([^\"]*)\" SC$")
    public void theLengthShouldBeSC(Integer arg0) {
        Integer theLengthOfString = (Integer) context.get("lengthString");
        logger.info("The length should be" + " - " + arg0);
        assertThat(theLengthOfString, Matchers.equalTo(arg0));
    }

    @Then("^the string should contain \"([^\"]*)\" SC$")
    public void theStringShouldContainSC(String arg0) {
        String theStringContain = (String) context.get("my_String");
        logger.info("The string should contain: " + arg0);
        assertThat(theStringContain, Matchers.containsString(arg0));
    }

    @Given("^I have a number \"([^\"]*)\" SC$")
    public void iHaveANumberSC(Integer arg0) {
        logger.info("I have a number " + arg0);
        context.put("theNumber", arg0);
    }

    @Then("the number should be even SC")
    public void theNumberShouldBeEvenSC() {
        Integer theNumber = (Integer) context.get("theNumber");
        logger.info("The even number: " + theNumber);
        assertThat(theNumber % 2, Matchers.equalTo(0));
    }

    @Then("the number should be odd SC")
    public void theNumberShouldBeOddSC() {
        Integer theNumber = (Integer) context.get("theNumber");
        logger.info("The number odd: " + theNumber);
        assertThat(theNumber % 2, Matchers.greaterThanOrEqualTo(0));
    }

    @Given("^I have a list with elements SC$")
    public void iHaveAListWithElementsSC(List<String> fruits) {
        logger.info("I have a list with elements " + fruits);
        context.put("theFruits", fruits);
    }

    @When("I check the size of the list SC")
    public void iCheckTheSizeOfTheListSC() {
        List<String> theFruits = (List<String>) context.get("theFruits");
        logger.info("Check the size of the list: " + theFruits.size());
        context.put("sizeList", theFruits.size());
    }

    @Then("^the size of the list should be \\\"([^\\\"]*)\\\" SC$")
    public void theSizeOfTheListShouldBeSC(Integer arg0) {
        Integer sizeList = (Integer) context.get("sizeList");
        logger.info("The size of the list: " + sizeList);
        assertThat(sizeList, Matchers.equalTo(arg0));
    }
}
