package Lilia_Rosca;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ro.Si;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class LRStepdefinition {
    Map<String, Object> context = new HashMap<>(); //pentru a trasminte parametru de la o etoda la alta

    @Given("I have a string {string}")
    public void iHaveAString(String string) {
        context.put("my_string", string);
    }

    @When("I check if it contains {string}")
    public void iCheckIfItContains(String subString) {
        String myString = (String) context.get("my_string");
        boolean contains = myString.contains(subString);
        context.put("contains", contains);
    }

    @Then("It should return true")
    public void itShouldReturnTrue() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    // reverse a string - 2 methods
    @When("I reverse the string")
    public void iReverseTheString() {
        String myString = (String) context.get("my_string");
        String reversedString = new StringBuilder(myString)
                .reverse()
                .toString();
        context.put("reversedString", String.valueOf(reversedString));
    }

    @Then("The reversed string should be {string}")
    public void theReversedStringShouldBe(String string) {
        String reversedString = (String) context.get("reversedString");
        assertThat(reversedString, equalTo(string));
    }

    // length of a string
    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String myString = (String) context.get("my_string");
        int len = myString.length(); // de modificat map
        context.put("length_of_string", len);
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(Integer num) {
        Integer actualLength = (Integer) context.get("length_of_string");
        assertThat(actualLength, equalTo(num));
    }

    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String docString) {
        context.put("paragrath", docString);
    }

    @When("I count the number of words")
    public void i_count_the_number_of_words() {
        String paragrath = (String) context.get("paragrath");
        int wordsCount = paragrath.split("(\\s|\\n)").length;
        context.put("words_count", wordsCount);
    }

    @Then("the word count should be {int}")
    public void the_word_count_should_be(Integer expectedWordsCount) {
        int actualWordsCount = (int) context.get("words_count");
        assertThat(actualWordsCount, equalTo(expectedWordsCount));
    }

    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> string) {
        context.put("my_list", string);
    }

    /* in trei pasi
        @When("I check if list contains {string}")
        public void iCheckIfListContainsBanana(String fruit) {
            List<String> list = (List<String>) context.get("my_list");
            boolean contains = list.contains(fruit);
            context.put("contains", contains);
        }
        @Then("list should contain banana")
        public void listShouldContain() {
            Boolean contains = (Boolean) context.get("contains");
            assertThat(contains, equalTo(true));
        }*/
    @Then("list should contain {string}")
    public void listShouldContain(String fruit) {
        List<String> list = (List<String>) context.get("my_list");
        assertThat(list, hasItem(fruit));
    }

}
