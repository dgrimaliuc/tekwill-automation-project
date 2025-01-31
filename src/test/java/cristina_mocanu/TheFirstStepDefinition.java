package cristina_mocanu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TheFirstStepDefinition {
    Map<String, Object> context = new HashMap<>();

    @Given("I have a string {string}")
    public void iHaveAString(String string) {
        context.put("my_string", string);
    }

    @When ("I check if it contains {string}")
    public void ICheckIfItContains (String subString) {
        String myString = (String) context.get("my_string");
        boolean contains = myString.contains(subString);
        context.put("contains", (contains));
    }
        @Then("I should return true")
        public void iShouldReturnTrue (){
        Boolean contains = (Boolean) context.get ("contains");
        assertThat(contains, equalTo(true));
}
//exercitiu 1

@Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String string) {
context.put("paragraph", string);
    }

    @When("I count the length of string")
    public void i_count_the_length_of_string() {
        String paragraph = (String) context.get("paragraph");
int stringLength = paragraph.length();
context.put("string_length", stringLength);
    }

    @Then("the length should be {int}")
    public void the_length_should_be(Integer expectedLength) {
        Integer actualLength = (Integer) context.get("string_length");
        assertThat(actualLength, equalTo(expectedLength));
    }

//    exercitiu 2

@Given("I have the numbers {int} and {int}")
public void i_have_the_numbers_10_and_4( int firstNumber, int secondNumber){

    context.put("firstNumber", 10);
    context.put("secondNumber", 4);
}
@When("i_subtract_the_second_number_from_the_first")
public void i_subtract_the_second_number_from_the_first(){
    int firstNumber = (int) context.get("firstNumber");
    int secondNumber = (int) context.get("secondNumber");
    int subtractNumber = firstNumber - secondNumber;
    context.put("subtractResult", subtractNumber);

}
    @Then("the result should be {int}")
    public void theResultShouldBe(int expectedResult) {
        int actualResult = (int) context.get("subtractResult");
        assertThat(actualResult, equalTo(expectedResult));

    }


//exerciciu 3

    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers (int a, int b) {
        context.put("a", a);
        context.put("b", b);
    }

    @When("I add the numbers")
    public void i_add_the_numbers() {
        int a = (int) context.get("a");
        int b = (int) context.get("b");
        int addResult = a + b;
        context.put("addResult", addResult);

    }
    @Then("the result should be {int}")
    public void the_result_should_be(int expectedAddResult) {
        int actualResult = (int) context.get("addResult");
        assertThat(actualResult, equalTo(expectedAddResult));
}

//exercitiu 4

    @Given("I have a number “{int}”")
    public void i_have_a_number(int x ) {
        context.put("x", x);

    }

    @Then("the number should be even")
    public void the_number_should_be_even() {
        int num = (int) context.get("x");
        boolean evenNumber = num % 2 == 0;
        assertThat(evenNumber, equalTo("true"));
    }
}