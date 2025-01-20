package sergiu_lungu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MyStepDef {

    Map<String, Object> context = new HashMap<>();
    private int number1;
    private int number2;
    private int result;
    private Integer number;

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

    @Then("It should returns true")
    public void itShouldReturnsTrue() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String myString = (String) context.get("my_string");

        int lenght = myString.length();
        context.put("lenght_of_string", lenght);
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(Integer num) {
        Integer actualLenght = (Integer) context.get("lenght_of_string");
        assertThat(actualLenght, equalTo(num));
    }

    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String docString) {
        context.put("paragraph", docString);
    }

    @When("I count the number of words")
    public void i_count_the_number_of_words() {
        String paragraph = (String) context.get("paragraph");
        int wordsCount = paragraph.split("\\s|\\n").length;
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

    @When("I check if list contains {string}")
    public void iCheckIfListContainsLemon(String fruit) {
        List<String> list = (List<String>) context.get("my_list");
        boolean contains = list.contains(fruit);
        context.put("contains", contains);
    }

    @Then("list should contain lemon")
    public void listShouldContainLemon() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    @Given("I have different paragraph:")
    public void iHaveDifferentParagraph(String paragraph) {
        context.put("paragraph", paragraph);
    }

    @When("I count the length of string")
    public void iCountTheLengthOfString() {
        String paragraph = (String) context.get("paragraph");
        int length = paragraph.length();
        context.put("length", length);
    }

    @Then("the length must to be {int}")
    public void theLengthMustToBe(int expectedLength) {
        int actualLength = (int) context.get("length");
        assertThat(actualLength, equalTo(expectedLength));
    }

    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers_and(Integer int1, Integer int2) {
        number1 = int1;
        number2 = int2;
    }
    @When("I subtract the second number from the first")
    public void i_subtract_the_second_number_from_the_first() {
        result = number1 - number2;
    }
    @Then("the result should be {int}")
    public void the_result_should_be(Integer expectedResult) {
        assertThat(result, equalTo(expectedResult));
    }

    @Given("I have a number “{int}”")
    public void iHaveANumber(Integer num) {
       number = num;
    }

    @Then("the number should be even")
    public void theNumberShouldBeEven() {
        boolean isEven = (number % 2 == 0);
        assertThat("The number is not even", isEven, is(true));
    }

    @Given("I have two numbers {int} and {int}")
    public void iHaveTwoNumbers(Integer num1, Integer num2){
        number1 = num1;
        number2 = num2;
    }

    @When("I add the numbers")
    public void iAddTheNumbers() {
        result = number1 + number2;
    }

    @Then("the result must be {int}")
    public void theResultMustdBe(Integer expectedResult) {
        assertThat(result, equalTo(expectedResult));
    }
}
