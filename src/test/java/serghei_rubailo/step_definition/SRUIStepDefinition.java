package serghei_rubailo.step_definition;

import com.google.common.collect.Ordering;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import serghei_rubailo.ui.shopify.pages.Shopify;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


public class SRUIStepDefinition {

    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(SRUIStepDefinition.class);
    HashMap<String, Object> stepResults = null;

    Shopify shopifyPage = new Shopify();

    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        stepResults = new HashMap<>();
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Given("Shopify Page is Open")
    public void shopifyPageIsOpen() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

    @When("Select price {string}")
    public void selectPriceUnder(String option) {
        if (option.equals("under $25"))
        {
            driver.findElement(shopifyPage.priceUnder25).click();
        } else if (option.equals("over $100")) {
            driver.findElement(shopifyPage.priceOver100).click();
        }
    }

    @Then("Verify price is {string}")
    public void verifyPriceIsUnder(String filter) {
        var els = driver.findElements(shopifyPage.cardPrice);

        for (var el : els) {
            var actual = Integer.parseInt(el.getText().substring(1));
            if (filter.equals("under $25"))
            {
                assertThat(actual, lessThanOrEqualTo(25));
            } else if (filter.equals("over $100")) {
                assertThat(actual, lessThanOrEqualTo(100));
            }
        }
    }

    @Then("Page Title is {string}")
    public void pageTitleIs(String expectedResult) {
        String actualResult = driver.getTitle();
        assertThat(actualResult, equalTo(expectedResult));
    }

    @When("Select price between {string}")
    public void selectPriceBetween$And$(String filterName) {
        if (filterName.equals("$25 and $50"))
        {
            driver.findElement(shopifyPage.price25to50).click();
        } else if (filterName.equals("$50 and $100")) {
            driver.findElement(shopifyPage.price50to100).click();
        } else {
            throw new RuntimeException("Filter not found: " + filterName);
        }
    }

    @Then("Verify price is between ${int} and ${int}")
    public void verifyPriceIsBetween$And$(int from, int to) {
        var els = driver.findElements(shopifyPage.cardPrice);

        for (var el : els) {
            var actual = Integer.parseInt(el.getText().substring(1));
            assertThat(actual, allOf(lessThanOrEqualTo(to),greaterThan(from)));
        }
    }

    @When("Select price over ${int}")
    public void selectPriceOver$(int price) {
        driver.findElement(shopifyPage.priceOver100).click();
    }


    @Then("Verify price is over ${int}")
    public void verifyPriceIsOver$(int price) {
        var els = driver.findElements(shopifyPage.cardPrice);

        for (var el : els) {
            var actual = Integer.parseInt(el.getText().substring(1));
            assertThat(actual, greaterThan(price));
        }
    }

    @When("Select color {string}")
    public void selectColor(String color) {
        driver.findElement(shopifyPage.getColorByName(color)).click();
    }


    @Then("Verify color is {string}")
    public void verifyColorIs(String color) {
        var els = driver.findElements(shopifyPage.cardColor);

        for (var el : els) {
            var actual = el.getAttribute("data-t");
            assertThat(actual, equalTo(color.toLowerCase()));
        }

    }

    @When("Select size {string}")
    public void selectSizeSize(String size) {
        driver.findElement(shopifyPage.getSizeByName(size)).click();
    }


    @Then("Verify size is {string}")
    public void verifySizeIsSize(String size) {
        var els = driver.findElements(shopifyPage.cardSize);

        for (var el : els) {
            var actual = el.getText();
            assertThat(actual, equalTo("Size: " + size));
        }
    }

    @When("Select gender {string}")
    public void selectGenderGender(String gender) {
        driver.findElement(shopifyPage.getGenderByName(gender)).click();
    }


    @Then("Verify gender is {string}")
    public void verifyGenderIs(String gender) {
        var els = driver.findElements(shopifyPage.cardGender);

        for (var el : els) {
            var actual = el.getText();
            assertThat(actual, equalTo(gender.toLowerCase()));
        }
    }

    @Then("Verify price is under ${int} and over ${int}")
    public void verifyPriceIsUnder$AndOver$(int under, int over) {
        var els = driver.findElements(shopifyPage.cardPrice);

        for (var el : els) {
            var actual = Integer.parseInt(el.getText().substring(1));
            assertThat(actual,
                    anyOf(lessThanOrEqualTo(under), greaterThan(over)));
        }

    }

    @Then("The message {string} is displayed")
    public void theMessageIsDisplayed(String message) {
        var els = driver.findElement(shopifyPage.nothingToShowMessage);
        assertThat(message, equalTo(els.getText()));
    }

    @Then("Verify colors are {string} or {string}")
    public void verifyColorsAreOr(String color1, String color2) {
        var els = driver.findElements(shopifyPage.cardColor);

        for (var el : els) {
            var actual = el.getAttribute("data-t");
            assertThat(actual,
                    anyOf(equalTo(color1.toLowerCase()), equalTo(color2.toLowerCase()))
            );
        }
    }

    @Then("Verify sizes are {string} or {string}")
    public void verifySizesAreOr(String size1, String size2) {
        var els = driver.findElements(shopifyPage.cardSize);

        for (var el : els) {
            var actual = el.getText();
            assertThat(actual,
                    anyOf(equalTo("Size: " + size1), equalTo("Size: " + size2))
            );
        }
    }

    @Then("Verify genders are {string} or {string}")
    public void verifyGendersAreOr(String gender1, String gender2) {
        var els = driver.findElements(shopifyPage.cardGender);

        for (var el : els) {
            var actual = el.getText();
            assertThat(actual,
                    anyOf(equalTo(gender1.toLowerCase()), equalTo(gender2.toLowerCase()))
            );
        }
    }

    @When("Sorting is selected {string}")
    public void sortingIsSelected(String option) {
        driver.findElement(shopifyPage.pageFilter).click();
        wait.until(ExpectedConditions.elementToBeClickable(shopifyPage.getSortFilterOption(option))).click();
    }


    @Then("Verify that elements sorted in {string} order")
    public void verifyThatElementsSortedInOrder(String option) {

        boolean expected;
        var els = driver.findElements(shopifyPage.cardPrice);
        var prices = els.stream().map(webElement -> Integer.parseInt(webElement.getText().substring(1)));

        boolean actual = Ordering.natural().isOrdered(prices.collect(Collectors.toList()));

        expected = switch (option) {
            case "Ascending" -> true;
            case "Descending" -> false;
            default -> throw new AssertionError("Wrong option");
        };

        assertThat(actual, equalTo(expected));

    }

    @When("Collect first item details")
    public void collectFirstItemDetails() {
        WebElement card = driver.findElement(shopifyPage.card);

        String title = card.findElement(shopifyPage.cardTitle).getText();
        String price = card.findElement(shopifyPage.cardPrice).getText().substring(1);
        String size = card.findElement(shopifyPage.cardSize).getText();
        String color = card.findElement(shopifyPage.cardColor).getAttribute("data-t");
        String img = card.findElement(shopifyPage.cardImage).getAttribute("src");

        HashMap<String, String> cardDetails = new HashMap<>();

        cardDetails.put("title", title);
        cardDetails.put("price", price);
        cardDetails.put("size", size);
        cardDetails.put("color", color);
        cardDetails.put("img", img);

        stepResults.put("card-details", cardDetails);

    }

    @When("Add the item to cart")
    public void addTheItemToCart() {
        WebElement card = driver.findElement(shopifyPage.card);
        card.findElement(shopifyPage.cardAddButton).click();
    }

    @When("Open cart")
    public void openCart() {
        driver.findElement(shopifyPage.cartButton).click();
    }

    @Then("Verify that cart is not empty")
    public void verifyThatCartIsNotEmpty() {
        HashMap<String, String> expectedCartDetails = (HashMap<String, String>) stepResults.get("card-details");

        var cartItems = driver.findElements(shopifyPage.cartItems);
        assertThat(cartItems.size(),equalTo(1));

        WebElement cartItem = cartItems.get(0);
        String title = cartItem.findElement(shopifyPage.cartItemTitle).getText();
        String price = cartItem.findElement(shopifyPage.cartItemPrice).getText();
        String color = cartItem.findElement(shopifyPage.cartItemColor).getText();
        String size = cartItem.findElement(shopifyPage.cartItemSize).getText();
        String image = cartItem.findElement(shopifyPage.cartItemImage).getAttribute("src");

        assertThat(title, equalTo(expectedCartDetails.get("title")));
        assertThat(price, equalTo("Price: $" + expectedCartDetails.get("price")));
        assertThat(color, equalTo("Color: " + expectedCartDetails.get("color")));
        assertThat(size, equalTo(expectedCartDetails.get("size")));
        assertThat(image, equalTo(expectedCartDetails.get("img")));

    }

    @When("User clicks on Plus button")
    public void userClicksOnPlusButton() {
        driver.findElement(shopifyPage.cartItemPlusButton).click();
    }

    @Then("Verify item quantity is {string}")
    public void verifyItemQuantityIs(String quantity) {
        String actualQuantity = driver.findElement(shopifyPage.cartItemQuantity).getText();
        assertThat(actualQuantity, equalTo(quantity));
    }

    @Then("Verify price is changed {int} times")
    public void verifyPriceIsChangedTimes(int n) {
        HashMap<String, String> expectedCartDetails = (HashMap<String, String>) stepResults.get("card-details");
        int expectedPrice = Integer.parseInt(expectedCartDetails.get("price"));

        var cartItems = driver.findElements(shopifyPage.cartItems);
        WebElement cartItem = cartItems.get(0);

        String actualItemPrice = cartItem.findElement(shopifyPage.cartItemPrice).getText();
        String actualTotalPrice = driver.findElement(shopifyPage.cartTotalPrice).getText();

        int total = expectedPrice * n;

        assertThat(actualItemPrice, equalTo("Price: $" + total));
        assertThat(actualTotalPrice, equalTo("Total: $" + total));
    }

    @When("User clicks on Minus button")
    public void userClicksOnMinusButton() {
        driver.findElement(shopifyPage.cartItemMinusButton).click();
    }


    @Then("Verify {string} message is shown")
    public void verifyMessageIsShown(String message) {
        String cartTitle = driver.findElement(shopifyPage.cartEmptyCartTitle).getText();
        assertThat(cartTitle, equalTo(message));
    }
}
