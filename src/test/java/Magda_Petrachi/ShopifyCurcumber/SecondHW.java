package Magda_Petrachi.ShopifyCurcumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class SecondHW {
    String str;
    int a, b, result;
    String aString, bString, resultString;
    boolean isTrue;

    private List<String> stringList;
    private Map<String, String> stringMap;
    private String retrievedValue;
    private List<Integer> intList;

    private Map<String, String> map;
    private Integer expectedSize;

    @Given("I have a list with elements MP")
    public void iHaveAListWithElementsMP(List<String> list) {
        this.stringList = list;
    }


    @When("I check the size of the list MP")
    public void iCheckTheSizeOfTheListMP() {
        result = stringList.size();
        System.out.println("Lunginea listei este " + stringList);
    }

    @Then("the size of the list should be {int} MP")
    public void theSizeOfTheListShouldBeMP(int expectedSize) {
        MatcherAssert.assertThat(result, equalTo(expectedSize));
    }
}

