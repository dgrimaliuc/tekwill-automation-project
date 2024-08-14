
package IgorTabirta.stepDef;

import denis_grimaliuc.actions.BaseActions;
import IgorTabirta.UI.ShopifyIT;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static denis_grimaliuc.ui.shopify.pages.Shopify.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UIStepDefinition {
    ShopifyIT shopifyPage = new ShopifyIT();
    BaseActions actions = null;
    WebDriver driver = null;
    WebDriverWait wait = null;
    //    Logger log = Logger.getLogger(UIStepDefinition.class);
    HashMap<String, Object> stepResults = null;


    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver127.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        stepResults = new HashMap<>();
        actions = new BaseActions(driver);
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("Adopt Page is Open")
    public void adoptPageIsOpen() {
        driver.get("https://petstore-eb41f.web.app/");
    }

    @Then("Verify URL of Page")
    public void verifyURLOfPage() {
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl, equalTo("https://petstore-kafka.swagger.io/?location=Chisinau"));
    }

    @Given("Shopify Page is Open")
    public void shopifyPageIsOpen() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

    @When("Select price {string}")
    public void selectPriceUnder(String option) {
        WebElement filter;
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
    public void verifyPriceIsUnder$(String filter) {
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

    @Then("Verify page title")
    public void verifyPageTitle() {
        String pageTitle = driver.getTitle();
        assertThat(pageTitle, equalTo("Ecommerce Website Template"));
    }

    @When("Select price between {string} IT")
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
            String text = el.getText();
            assertThat(text, equalTo(gender.toLowerCase()));
        }
    }

    @Then("Verify price is Under $25 and Over $100")
    public void verifyPriceIsUnder$AndOver$() {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual,
                    either(lessThanOrEqualTo(25)).or(greaterThanOrEqualTo(100))
            );
        }
    }

    @Then("The Message {string} is displayed")
    public void theMessageIsDisplayed(String message) {
        WebElement cardContainer = driver.findElement(shopifyPage.cardContainer);
        String containerText = cardContainer.getText();
        assertThat(containerText, equalTo(message));
    }

    //        driver.switchTo().alert()
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

        if (sortingType.equals("Ascending")) {
//            List<Integer> copy = new ArrayList<>(sortedPrices);
//            Collections.sort(copy);
//            assertThat(sortedPrices, equalTo(copy));
            for (int i = 0; i < sortedPrices.size() - 1; i++) {
                Integer expectedPrice = sortedPrices.get(i);
                Integer actualPrice = Integer.parseInt(driver.findElements(shopifyPage.cardPrices).get(i).getText().substring(1));
                assertThat(actualPrice, equalTo(expectedPrice));
            }
        }
    }


    @When("Collect first item details")
    public void collectFirstItemDetails() {
        WebElement firstItem = driver.findElement(shopifyPage.firstItem);
        String title = firstItem.findElement(By.id("card-title")).getText();
        String price = firstItem.findElement(By.id("card-price")).getText();
        String color = firstItem.findElement(shopifyPage.cardColors).getAttribute("data-t");
        String size = firstItem.findElement(By.id("card-size")).getText();
        String image = firstItem.findElement(By.id("card-image")).getAttribute("src");


        Map<String, String> itemDetails = new HashMap<>();
        itemDetails.put("title", title);
        itemDetails.put("price", price);
        itemDetails.put("color", color);
        itemDetails.put("size", size);
        itemDetails.put("image", image);


        stepResults.put("item_details", itemDetails);


    }

    @When("Add to cart first item")
    public void addToCartFirstItem() {
        WebElement button = driver.findElement(shopifyPage.addToCart);
        button.click();
        actions.waitUntilPageToLoad();

    }

    @Then("Verify cart is not empty")
    public void verifyCartIsNotEmpty() {
        Map<String, String> itemDetails = (Map<String, String>) stepResults.get("item_details");

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
        WebElement emptyCart = driver.findElement(shopifyPage.emptyCardTitle);

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
        assertThat(quantityField.getText(), equalTo(quantity));
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
        WebElement plusButton = driver.findElement(shopifyPage.minusButton);
        plusButton.click();
    }
}

