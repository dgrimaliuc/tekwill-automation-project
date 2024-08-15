package chiril_bortnicov.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.codehaus.groovy.classgen.asm.BytecodeDumper;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import chiril_bortnicov.ui.shopify.pages.Shopify;

import static chiril_bortnicov.ui.shopify.pages.Shopify.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class UIStepDefinition {
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);
    HashMap<String, Object> stepResults = null;

    Shopify shopifyPage = new Shopify();


    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver-127.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver, 10);
//        actions = new BaseActions(driver);
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

    @Then("Select price {string}")
    public void selectPrice(String option) {
        WebElement filter = null;
        if (option.equals("Under $25")) {
            filter = driver.findElement(shopifyPage.priceUnder25);
        } else if (option.equals("Over $100")) {
            filter = driver.findElement(shopifyPage.priceOver100);
        } else {
            throw new RuntimeException("Filter not found: " + option);
        }
        filter.click();
    }

    @Then("Verify price is {string}")
    public void verifyPriceIsUnder(String filter) {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);

            if (filter.equals("Under $25")) {
                assertThat(actual, lessThanOrEqualTo(25));
            } else if (filter.equals("Over $100")) {
                assertThat(actual, greaterThanOrEqualTo(100));
            }
        }
    }

    @When("Select price between {string}")
    public void selectPriceBetween$And$(String filterName) {
        WebElement filter;
        if (filterName.equals("$25 to $50")) {
            filter = driver.findElement(shopifyPage.price25to50);
        } else if (filterName.equals("$50 to $100")) {
            filter = driver.findElement(shopifyPage.price50to100);
        } else {
            throw new RuntimeException("Filter not found: " + filterName);
        }

        filter.click();
    }

    @Then("Verify price is between ${int} and ${int}")
    public void verifyPriceIsBetween$And$(int min, int max) {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);

            assertThat(actual,
                    allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))
            );
        }
    }

    @When("Select price over $100")
    public void selectPriceOver$() {
        driver.findElement(shopifyPage.priceOver100).click();
    }

    @Then("Verify price is over $100")
    public void verifyPriceIsOver$() {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);

            assertThat(actual, greaterThanOrEqualTo(100));
        }
    }

    @When("Select color {string}")
    public void selectColor(String color) {
        WebElement filter = driver.findElement(getColorByName(color));
        filter.click();
    }

    @Then("Verify color is {string}")
    public void verifyColorIs(String color) {
        var els = driver.findElements(shopifyPage.cardColors);
        for (WebElement el : els) {
            String actual = el.getAttribute("data-t");
            assertThat(actual, equalTo(color.toLowerCase()));
        }
    }

    @When("Select size {string}")
    public void selectSize(String size) {
        WebElement filter = driver.findElement(getSizeByName(size));
        filter.click();
    }

    @Then("Verify size is {string}")
    public void verifySizeIs(String size) {
        var sizes = driver.findElements(shopifyPage.cardSizes);
        for (WebElement el : sizes) {
            String actual = el.getText();
            assertThat(actual, equalTo("Size: " + size));
        }
    }

    @When("Select gender {string}")
    public void selectGender(String gender) {
        WebElement filter = driver.findElement(getGenderByName(gender));
        filter.click();
    }

    @Then("Verify gender is {string}")
    public void verifyGenderIs(String gender) {
        var genders = driver.findElements(shopifyPage.cardGenders);
        for (var el : genders) {
            String txt = el.getText();
            assertThat(txt, equalTo(gender.toLowerCase()));
        }
    }

    @Then("Verify price is Under $25 and Over $100")
    public void verifyPriceIsUnder$AndOver$() {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual, either(lessThanOrEqualTo(25)).or(greaterThanOrEqualTo(100)));
        }
    }

    @Then("The message {string} is displayed")
    public void theMessageIsDisplayed(String message) {
        WebElement cardContainer = driver.findElement(shopifyPage.cardContainer);
        String containerTxt = cardContainer.getText();
        assertThat(containerTxt, equalTo(message));
    }

    @Then("Verify colors is {string} and {string}")
    public void verifyColorsIsAnd(String color1, String color2) {
        var els = driver.findElements(shopifyPage.cardColors);
        for (WebElement el : els) {
            String actual = el.getAttribute("data-t");
            assertThat(actual, CoreMatchers.anyOf(equalTo(color1.toLowerCase()), equalTo(color2.toLowerCase())));
        }
    }

    @Then("Verify sizes is {string} and {string}")
    public void verifySizesIsAnd(String size1, String size2) {
        var sizes = driver.findElements(shopifyPage.cardSizes);
        for (WebElement el : sizes) {
            String actual = el.getText();
            assertThat(actual, CoreMatchers.anyOf(equalTo("Size: " + size1), equalTo("Size: " + size2)));
        }
    }

    @Then("Verify genders is {string} and {string}")
    public void verifyGendersIsAnd(String gender1, String gender2) {
        var genders = driver.findElements(shopifyPage.cardGenders);
        for (WebElement el : genders) {
            String actual = el.getText();
            assertThat(actual, isOneOf(gender1.toLowerCase(), gender2.toLowerCase()));
        }
    }

    @When("Select sorting {string}")
    public void selectSorting(String sortingType) {
        WebElement sortButton = driver.findElement(shopifyPage.sortingButton);
        sortButton.click();
        WebElement option = driver.findElement(getSortingOption(sortingType));
        option.click();
    }

    @When("I sort items by price")
    public void iSortItemsByPrice() {
        var prices = driver.findElements(shopifyPage.cardPrices);
        List<Integer> sortedPrices = new ArrayList<>();
        for (WebElement price : prices) {
            String stringPrice = price.getText().substring(1);
            Integer intPrice = Integer.parseInt(stringPrice);
            sortedPrices.add(intPrice);
        }
        Collections.sort(sortedPrices);
        stepResults.put("sortedPrices", sortedPrices);
    }

    @Then("Verify sorting is {string}")
    public void verifySortingIs(String sortingType) {
        List<Integer> sortedPrices = (List<Integer>) stepResults.get("sortedPrices");
        List<Integer> displayedPrices = driver.findElements(shopifyPage.cardPrices).stream()
                .map(e -> Integer.parseInt(e.getText().substring(1)))
                .collect(Collectors.toList());
        if (sortingType.equals("Ascending")) {
            for (int i = 0; i < sortedPrices.size(); i++) {
                Integer expectedPrice = sortedPrices.get(i);
                Integer actualPrice = displayedPrices.get(i);
                assertThat(actualPrice, equalTo(expectedPrice));
            }
        } else if (sortingType.equals("Descending")) {
            for (int i = 0; i < sortedPrices.size(); i++) {
                Integer expectedPrice = sortedPrices.get(sortedPrices.size() - 1 - i);
                Integer actualPrice = displayedPrices.get(i);
                assertThat(actualPrice, equalTo(expectedPrice));
            }
        }
    }


    @When("Add to cart first item")
    public void addToCartFirstItem() {
        WebElement button = driver.findElement(shopifyPage.addToCart);
        button.click();
    }

    @When("Collect first item details")
    public void collectFirstItemDetails() {
        WebElement firstItem = driver.findElement(shopifyPage.firstItem);
        String title = firstItem.findElement(By.id("card-title")).getText();
        String price = firstItem.findElement(By.id("card-price")).getText();
        String color = firstItem.findElement(shopifyPage.cardColors).getAttribute("data-t");
        String size = firstItem.findElement(By.id("card-size")).getText();
        String gender = firstItem.findElement(By.id("card-gender")).getText();
        String image = firstItem.findElement(By.id("card-image")).getAttribute("src");

        Map<String, String> itemDetails = new HashMap<>();
        itemDetails.put("title", title);
        itemDetails.put("price", price);
        itemDetails.put("color", color);
        itemDetails.put("size", size);
        itemDetails.put("gender", gender);
        itemDetails.put("image", image);
        stepResults.put("item_details", itemDetails);

    }

    @Then("Verify cart is not empty")
    public void verifyCartIsNotEmpty() {
        Map<String, String> itemDetails = (Map<String, String>) stepResults.get("item_details");
        WebElement cartButton = driver.findElement(shopifyPage.cartButton);
        cartButton.click();
        var cartItems = driver.findElements(shopifyPage.cartItems);
        assertThat(cartItems.size(), equalTo(1));

        WebElement cartItem = cartItems.get(0);
        String title = cartItem.findElement(By.tagName("h4")).getText();
        String price = cartItem.findElement(shopifyPage.itemPrice).getText();
        String color = cartItem.findElement(By.xpath("//p[contains(text(),'Color')]")).getText();
        String size = cartItem.findElement(By.xpath("//p[contains(text(),'Size')]")).getText();
        String image = cartItem.findElement(By.tagName("img")).getAttribute("src");
        String total = driver.findElement(shopifyPage.totalPrice).getText();

        assertThat(title, equalTo(itemDetails.get("title")));
        assertThat(price, equalTo("Price: " + itemDetails.get("price")));
        assertThat(color, equalTo("Color: " + itemDetails.get("color")));
        assertThat(size, equalTo(itemDetails.get("size")));
        assertThat(image, equalTo(itemDetails.get("image")));
        assertThat(total, equalTo("Total: " + itemDetails.get("price")));

    }

    @When("Open cart")
    public void openCart() {
        WebElement cartButton = driver.findElement(shopifyPage.cartButton);
        cartButton.click();
    }

    @Then("Verify cart is empty")
    public void verifyCartIsEmpty() {
        WebElement emptyCart = driver.findElement(shopifyPage.emptyCartTitle);
        assertThat(emptyCart.getText(), equalTo("Your cart is empty"));
    }

    @When("User click on Plus button")
    public void userClickOnPlusButton() {
        WebElement plusButton = driver.findElement(shopifyPage.plusButton);
        plusButton.click();
    }

    @Then("Verify item quantity is {string}")
    public void verifyItemQuantityIs(String quantity) {
        WebElement quantityField = driver.findElement(shopifyPage.itemQuantity);
        assertThat(quantityField.getText(), equalTo((quantity)));
    }

    @Then("Verify price was increased {int} times")
    public void verifyPriceWasIncreasedTimes(int quantity) {
        Map<String, String> itemDetails = (Map<String, String>) stepResults.get("item_details");
        String stringPrice = itemDetails.get("price").substring(1);
        int price = Integer.parseInt(stringPrice);
        int total = price * quantity;

        String itemActualPrice = driver.findElement(shopifyPage.itemPrice).getText();
        String actualTotalPrice = driver.findElement(shopifyPage.totalPrice).getText();

        assertThat(itemActualPrice, equalTo("Price: $" + total));
        assertThat(actualTotalPrice, equalTo("Total: $" + total));
    }

    @When("User click on Minus button")
    public void userClickOnMinusButton() {
        WebElement minusButton = driver.findElement(shopifyPage.minusButton);
        minusButton.click();
    }

    @When("User click on Plus button {int} times")
    public void userClickOnPlusButtonTimes(int times) {
        WebElement plusButton = driver.findElement(shopifyPage.plusButton);
        for (int i = 0; i < times; i++) {
            plusButton.click();
        }
    }

    @When("User click on Remove button")
    public void userClickOnRemoveButton() {
        WebElement removeButton = driver.findElement(shopifyPage.removeButton);
        removeButton.click();
    }

    @Then("Verify cart is not displayed")
    public void verifyCartIsNotDisplayed() {
        WebElement cartWrapper = driver.findElement(shopifyPage.cartWrapper);
        assertThat(cartWrapper.isDisplayed(), equalTo(false));
    }

    @When("Add random item to cart {int} times")
    public void addRandomItemToCartTimes(int times) {
        List <WebElement> elements;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < times; i++) {
            elements = driver.findElements(shopifyPage.addToCart);
            int index = random.nextInt(elements.size());
            elements.get(index).click();
        }
    }

    @When("Find sum of all items")
    public void findSumOfAllItems() {
        var prices = driver.findElements(shopifyPage.itemPrice);
        int sum = 0;
        for (var price : prices){
            String stringPrices = price.getText().replaceAll("[^0-9]","");
            int intPrice = Integer.parseInt(stringPrices);
            sum += intPrice;
        }
        stepResults.put("price-sum", sum);
    }

    @Then("Verify total price is correct")
    public void verifyTotalPriceIsCorrect() {
        Integer expectedTotalPrice = (Integer) stepResults.get("price-sum");
        String actualTotalPrice = driver.findElement(shopifyPage.totalPrice).getText();
        assertThat(actualTotalPrice, equalTo("Total: $" + expectedTotalPrice));
    }

    @When("User click on Order button")
    public void userClickOnOrderButton() {
        WebElement orderButton = driver.findElement(shopifyPage.orderButton);
        orderButton.click();
    }

    @Then("Verify order is successful")
    public void verifyOrderIsSuccessful() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText, equalTo("Order has been placed!"));
        alert.accept();
    }
}
