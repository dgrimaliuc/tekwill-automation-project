package denis_grimaliuc;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class TheFirstStepDefinishn {

    Map<String, Object> context = new HashMap<>();

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

    @When("I reverse the string")
    public void i_reverse_the_string() {
        String myString = (String) context.get("my_string");
        String reversedString = new StringBuilder(myString)
                .reverse()
                .toString();

        context.put("reversed_string", reversedString);

    }

    @Then("the reversed string should be {string}")
    public void the_reversed_string_should_be(String string) {
        String reversedString = (String) context.get("reversed_string");
        assertThat(reversedString, equalTo(string));
    }


    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String myString = (String) context.get("my_string");

        int length = myString.length();
        context.put("length_of_string", length);
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(Integer num) {
        Integer actualLength = (Integer) context.get("length_of_string");
        assertThat(actualLength, equalTo(num));
    }


    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String docString) {
        context.put("paragraph", docString);
    }

    @When("I count the number of words")
    public void i_count_the_number_of_words() {
        String paragraph = (String) context.get("paragraph");
        int wordsCount = paragraph.split("(\\s|\\n)").length;
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

    @Then("list should contain {string}")
    public void listShouldContain(String fruit) {
        List<String> list = (List<String>) context.get("my_list");
        assertThat(list, hasItem(fruit));
    }
}
