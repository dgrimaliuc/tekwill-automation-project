package Serghei_Condrasov;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import serghei_condrasov.AdoptPagePets;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;

public class FirstUIStepsDefinition {
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger logger = Logger.getLogger(FirstUIStepsDefinition.class);
    Map<String, Object> context = new HashMap<>();
    AdoptPagePets page = null;

    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        page = new AdoptPagePets(driver);
    }

    @After
    public void after() {
        driver.quit();
    }


    @Given("Open Random location")
    public void openRandomLocation() {
        String randomLocation = RandomStringUtils.random(7, true, false);
        logger.debug("Open Random location: " + addQuotes(randomLocation));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocation);
        context.put("Random location", randomLocation);
    }

    @Then("Verify random location text is present in [Text Input], [Pets in ..] and [Adoptions in ..]")
    public void verifyLocationSC() {
        String randomLocation = (String) context.get("Random location");
        logger.debug("Verify Pets in: " + addQuotes(randomLocation));
        assertThat(page.petsInTitle.getText(), Matchers.equalTo("Pets in " + randomLocation));

        logger.debug("Adoptions in: " + addQuotes(randomLocation));
        assertThat(page.adoptionsInTitle.getText(), Matchers.equalTo("Adoptions in " + randomLocation));

        logger.debug("Text Input: " + addQuotes(randomLocation));
        assertThat(page.locationInput.getAttribute("value"), Matchers.equalTo(randomLocation));
    }

    @Then("Verify current section contains default info in [The game]")
    public void verifyDefaultTextInTheGameSectionSC() {
        logger.debug("Verify current section: " + page.numberOfPetsInfo.getText());
        assertThat(page.numberOfPetsInfo.getText().trim(), Matchers.equalTo("No pets. Go rescue some pets!"));

        logger.debug("Verify current section: " + page.adoptionsOfPetsInfo.getText());
        assertThat(page.adoptionsOfPetsInfo.getText().trim(), Matchers.equalTo("No adoptions. Go get those pets adopted!"));
    }
}

