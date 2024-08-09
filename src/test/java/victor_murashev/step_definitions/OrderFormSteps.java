package victor_murashev.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import victor_murashev.hooks.WebDriverHooks;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class OrderFormSteps {

    private final WebDriver driver = WebDriverHooks.getDriver();
    private final WebDriverWait wait = WebDriverHooks.getWait();
    private final Logger log = Logger.getLogger(OrderFormSteps.class.getName());

    @Given("the user is on the {string}")
    public void theUserIsOnThe(String url) {
        driver.get(url);
        WebDriverHooks.pauseExecution(500);

    }

    @When("the user clicks the {string} button to open order")
    public void theUserClicksTheButtonToOpenOrder(String buttonBuyNow) {
        WebElement buyNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[contains(text(),'" + buttonBuyNow + "')]")));
        buyNowButton.click();

        WebDriverHooks.pauseExecution(500);
    }

    @When("the user fills in the \"Name\" field with {string}")
    public void theUserFillsInTheNameFieldWith(String name) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        nameField.sendKeys(name);

        WebDriverHooks.pauseExecution(500);
    }

    @When("the user fills in the \"Phone\" field with {string}")
    public void theUserFillsInThePhoneFieldWith(String phone) {
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone")));
        phoneField.sendKeys(phone);

        WebDriverHooks.pauseExecution(500);
    }

    @When("the user selects {string} and sets the quantity to {string}")
    public void theUserSelectsAndSetsTheQuantityTo(String coffeeType, String quantity) {
        WebElement coffeeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'" + coffeeType + "')]")));
        if (!coffeeCheckbox.isSelected()) {
            coffeeCheckbox.click();
        }

        WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(coffeeType)));
        //quantityField.clear();
        quantityField.sendKeys(Keys.chord(Keys.CONTROL + "a", Keys.DELETE));
        quantityField.sendKeys(quantity);

        WebDriverHooks.pauseExecution(500);
    }

    @When("the user clicks the {string} button to calculate order")
    public void theUserClicksTheButtonToCalculateOrder(String buttonCalculate) {
        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[contains(text(),'" + buttonCalculate + "')]")));
        calculateButton.click();

        WebDriverHooks.pauseExecution(500);
    }

    @Then("the displayed price should be: {string} Lei")
    public void theDisplayedPriceShouldBeLei(String totalAmount) {
        WebElement priceDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Your price will be')]")));
        String priceText = priceDisplay.getText();

        String extractedPrice = priceText.replaceAll("\\D+", "");

        assertEquals("Expected price does not match!", Integer.parseInt(totalAmount), Integer.parseInt(extractedPrice));

        WebDriverHooks.pauseExecution(500);
    }
}
