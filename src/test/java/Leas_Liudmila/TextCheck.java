package Leas_Liudmila;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextCheck {
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(TextCheck.class);

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

    }

    @After
    public void after() {
        driver.close();
    }

    @Given("Adopt Page with custom location is open")
    public void PageIsOpen() {
        driver.get("https://petstore-kafka.swagger.io/?location=Boston");
    }

    @When("{string} text is present in [Text Input], [Pets in ..] and [Adoptions in ..]")
    public void textIsPresentInTextInputPetsInAndAdoptionsIn(String my_location) {
        log.debug("Verify that my location is displayed: " + addQuotes(my_location));
        WebElement text_input = driver.findElement(By.xpath("//input[@id='location-input']"));
        WebElement petsIn = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[1]//h2"));
        WebElement adoptionIn = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[2]/h2"));

        assertThat(text_input.getAttribute("value"), Matchers.equalTo(my_location));
        assertThat(petsIn.getText(), Matchers.equalTo("Pets in " + my_location));
        assertThat(adoptionIn.getText(), Matchers.equalTo("Adoptions in " + my_location));

    }

    @When("Verify current section contains default info in [The game] section {string} , {string} , {string} , {string}")
    public void verifyCurrentSectionContainsDefaultInfoInTheGameSection(String info1, String info2, String info3, String info4) {
        log.debug(String.format("Verify that info is displayed: \n" + addQuotes(info1) + "\n" + addQuotes(info2) +
                "\n" + addQuotes(info3) + "\n" + addQuotes(info4)));
        WebElement nameOfSection = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/h2"));
        WebElement firstRow1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/p[1]"));
        WebElement secondRow2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/p[2]"));
        WebElement thirdRow3 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/p[3]"));

        wait.until(ExpectedConditions.textToBePresentInElement(firstRow1, info2));

        assertThat(nameOfSection.getText(), Matchers.equalTo(info1));
        assertThat(firstRow1.getText(), Matchers.equalTo(info2));
        assertThat(secondRow2.getText(), Matchers.equalTo(info3));
        assertThat(thirdRow3.getText(), Matchers.equalTo(info4));
    }
}



