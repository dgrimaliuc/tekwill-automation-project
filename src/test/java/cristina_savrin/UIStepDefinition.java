package cristina_savrin;

import helpers.Helpers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static helpers.Helpers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class UIStepDefinition {
    WebDriver driver = null;
    WebDriverWait wait = null;
    //String customLocation = "Paris";
    Logger log = Logger.getLogger(UIStepDefinition.class);

    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=9222");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        stepResults = new ArrayList<>();
    }

    @After
    public void after() {
        driver.close();
    }

    @Given("Open AdoptPage with random location")
    public void randomLocation() {
        String randomLocation = RandomStringUtils.random(10, true, true);
        log.debug("Open random location: " + addQuotes(randomLocation));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocation);
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='root']/div/div[3]/div[1]//h2"), "Pets in " + randomLocation));
        stepResults.add(randomLocation);
    }

    @Then("Verify random location {string} in [Text Input], [Pets in ..] and [Adoptions in ..]")
    public void verifyRandomLocation(String location) {
        location = Helpers.getValue(location, String.class);
        log.debug("Verify random location is displayed: " + addQuotes(location));

        WebElement input = driver.findElement(By.xpath("//input[@id='location-input']"));
        WebElement petsLocationEl = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[1]//h2"));
        WebElement adoptTitle = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[2]/h2"));

        assertThat(petsLocationEl.getText(), Matchers.equalTo("Pets in " + location));
        assertThat(adoptTitle.getText(), Matchers.equalTo("Adoptions in " + location));
        assertThat(input.getAttribute("value"), Matchers.equalTo(location));
    }

    @And("Verify [The game] section")
    public void verifyTheGameSection() {
        ArrayList<String> gameExpected = new ArrayList<>();
        gameExpected.add("The game");
        gameExpected.add("WebSocket messages: 1");
        gameExpected.add("No pets. Go rescue some pets!");
        gameExpected.add("No adoptions. Go get those pets adopted!");

        log.debug("Verify current section contains default info in [The game] section");
        WebElement title = driver.findElement(By.xpath("//div[@class='p-8']/h2"));
        WebElement websocket = driver.findElement(By.xpath("//div[@class='p-8']/p[1]"));
        WebElement pets = driver.findElement(By.xpath("//div[@class='p-8']/p[2]"));
        WebElement adoptions = driver.findElement(By.xpath("//div[@class='p-8']/p[3]"));

        ArrayList<String> gameActual = new ArrayList<>();
        gameActual.add(title.getText().trim());
        waitInSeconds(5);
        gameActual.add(websocket.getText().trim());
        gameActual.add(pets.getText().trim());
        gameActual.add(adoptions.getText().trim());

        assertEquals(gameExpected, gameActual);
    }
}