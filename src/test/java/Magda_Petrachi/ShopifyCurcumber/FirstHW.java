package Magda_Petrachi.ShopifyCurcumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

public class FirstHW {

    String str;
    int a, b, result;
    String aString, bString, resultString;
    boolean isTrue;

    @Given("I have a string {string} MP")
    public void iHaveAStringMP(String arg0) {
        str = arg0;
    }

    @When("I check the length of the string MP")
    public void iCheckTheLengthOfTheStringMP() {
        int length = str.length();
        result = length;
        System.out.println("Lunginea textului este " + length);
    }

    @Then("the length should be {int} MP")
    public void theLengthShouldBeMP(int arg0) {
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(arg0));
    }


    @Then("the string should contain {string} MP")
    public void theStringShouldContainMP(String subString) {
        MatcherAssert.assertThat(str, CoreMatchers.containsString(subString));
        System.out.println("Textul contine " + subString);
    }

    @Given("I have a number {int} MP")
    public void iHaveANumberMP(int arg0) {
        a = arg0;
        System.out.println("Numarul este " + a);
    }

    @When("I check if the number is even MP")
    public void iCheckIfTheNumberIsEvenMP() {
        isTrue = (a % 2 == 0);
    }

    @Then("the number should be even MP")
    public void theNumberShouldBeEvenMP() {
        MatcherAssert.assertThat(isTrue, CoreMatchers.is(true));
        System.out.println("Numarul este par");
    }
}
