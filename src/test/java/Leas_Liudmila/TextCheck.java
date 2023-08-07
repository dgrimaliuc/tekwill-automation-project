package Leas_Liudmila;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextCheck {
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(TextCheck.class);

    LLAdoptPage myPageLL = null;

    Map<String, Object> context = new HashMap<>();


    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        myPageLL = new LLAdoptPage(driver);

    }

    @After
    public void after() {
        driver.close();
    }

    @Given("Adopt Page with random location is open")
    public void pageWRandomLocation() {
        String randomLocationLL = RandomStringUtils.random(10, true, true);
        log.debug("Open Custom locations: " + addQuotes(randomLocationLL));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocationLL);
        context.put("randomLocation", randomLocationLL);
    }

    @When("Random location is present in [Text Input], [Pets in ..] and [Adoptions in ..]")
    public void randomLocationIsPresentInTextInputPetsInAndAdoptionsIn() {
        String randomLocation = (String) context.get("randomLocation");
        log.debug("Verify that my location is displayed: " + addQuotes(randomLocation));

        assertThat(myPageLL.myLocationInput.getAttribute("value"), Matchers.equalTo(randomLocation));
        assertThat(myPageLL.petsInTitle.getText(), Matchers.equalTo("Pets in " + randomLocation));
        assertThat(myPageLL.adoptionsInTitle.getText(), Matchers.equalTo("Adoptions in " + randomLocation));
    }

    @When("Verify current section contains default info in [The game] section {string} , {string} , {string} , {string}")
    public void verifyCurrentSectionContainsDefaultInfoInTheGameSection(String info1, String info2, String info3, String info4) {
        log.debug(String.format("Verify that info is displayed: \n" + addQuotes(info1) + "\n" + addQuotes(info2) +
                "\n" + addQuotes(info3) + "\n" + addQuotes(info4)));

        wait.until(ExpectedConditions.textToBePresentInElement(myPageLL.theGame.webSocketMsg, info2));

        assertThat(myPageLL.theGame.title.getText(), Matchers.equalTo(info1));
        assertThat(myPageLL.theGame.webSocketMsg.getText(), Matchers.equalTo(info2));
        assertThat(myPageLL.theGame.petsInInfo.getText(), Matchers.equalTo(info3));
        assertThat(myPageLL.theGame.adoptionsInInfo.getText(), Matchers.equalTo(info4));
    }


}



