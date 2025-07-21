package example.actions;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static example.data.enums.OS.MAC;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BaseActions {

    static Logger log = Logger.getLogger(BaseActions.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    public Object shouldBeDisplayed;
    private Component arrowRight;

    public BaseActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5, 200);
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }

    public static void setDefaultTimeouts(WebDriver driver) {
        setTimeouts(driver, 5);
    }

    public static void setTimeoutsToMin(WebDriver driver) {
        setTimeouts(driver, 2);
    }

    public static void setTimeouts(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, SECONDS);
        
    }

    public void whaitForCurrentURLmatchers(String matches) {
        log.trace("Whait for curent URL matchers " + matches);
        wait.until(ExpectedConditions.urlMatches(matches));
    }

    public void leftClick(WebElement element) {
        log.trace("Clicking element: " + element);
        new Actions(driver).moveToElement(element).click(element).perform();
    }

    public void waitForCurrentURLContains(String url) {
        log.trace("Waiting for current URL contains: " + url);
        wait.until(ExpectedConditions.urlContains(url));
    }

    public Object executeScript(String script, Object object) {
        return ((JavascriptExecutor) driver).executeScript(script, object);
    }

    public void waitForNumberOfElements(List<WebElement> elements, int count) {
        log.trace("Waiting for number of elements: " + count);
        setTimeoutsToMin(driver);
        wait.until(driver -> elements.size() == count);
        setDefaultTimeouts(driver);
    }

    public void waitForBackgroundColor(WebElement element, String color) {
        log.trace("Waiting for background color: " + color);
        wait.until(attributeToBe(element, "background-color", color));
        // driver -> element.getCssValue("background-color").equals(color)
    }

    public void shouldNotHaveAttribute(WebElement element, String attribute, String value) {
        wait.until(not(attributeToBe(element, attribute, value)));
    }

    public void shouldHaveAttribute(WebElement element, String attribute, String value) {
        wait.until(attributeToBe(element, attribute, value));
    }

    public void shouldHaveAttributeContains(WebElement element, String attribute, String value) {
        wait.until((ExpectedConditions.attributeContains(element, attribute, value)));
    }

    public void waitForNumberOfElements(Components<?> elements, int count) {
        log.trace("Waiting for number of elements: " + count);
        wait.until(driver -> elements.size() == count);
    }

    public void shouldHaveTextContains(WebElement element, String text) {
        shouldHaveTextContains(element, text, false);
    }

    public void shouldHaveTextContains(WebElement element, String text, boolean ignoreCase) {
        log.trace("Checking if element has text: " + element);
        if (ignoreCase) {
            wait.until(driver -> element.getText().toLowerCase().contains(text.toLowerCase()));
        } else {
            wait.until(driver -> element.getText().contains(text));
        }
    }

    public void shouldHaveTextToBe(WebElement element, String text) {
        log.trace("Checking if element has text: " + element);
        wait.until(textToBePresentInElement(element, text));
    }

    public void shouldBeDisabled(WebElement element) {
        log.trace("Checking if element is disabled: " + element);
        wait.until(ExpectedConditions.attributeToBe(element, "disabled", "true"));
    }

    public void shouldNotHaveTextToBe(WebElement element, String text) {
        log.trace("Checking if element has text: " + element);
        wait.until(not(textToBePresentInElement(element, text)));
    }

    public void shouldHaveTextEndsWith(WebElement element, String text) {
        log.trace("Checking if element has text: " + element);
        wait.until(driver -> element.getText().endsWith(text));
    }

    public void shouldSee(WebElement element) {
        log.trace("Checking if element is visible: " + element);
        setTimeoutsToMin(driver);
        wait.until(driver -> {
            try {
                return isInView(element);
            } catch (Exception e) {
                return false;
            }
        });
        setDefaultTimeouts(driver);
    }

    public void shouldNotBeDisplayed(WebElement element) {
        log.trace("Checking if element is not visible: " + element);
        setTimeoutsToMin(driver);
        wait.until(d -> {
            boolean isDisplayed;
            try {
                isDisplayed = element.isDisplayed();
            } catch (Exception e) {
                isDisplayed = false;
            }
            return !isDisplayed;
        });
        setDefaultTimeouts(driver);
    }

    public void waitUntilPageToLoad() {
        log.trace("Waiting for page to load");
        wait.until(driver -> executeScript("return document.readyState", null).equals("complete"));
    }

    public void clickWithJS(WebElement element) {
        log.trace("Clicking element with JS: " + element);
        executeScript("arguments[0].click();", element);
    }

    public void shouldBeDisplayed(WebElement element) {
        log.trace("Checking if element is displayed: " + element);
        try {
            wait.until(driver -> element.isDisplayed());
        } catch (Exception e) {
            throw new TimeoutException("Element is not displayed: " + element, e);
        }
    }

    public boolean isInView(WebElement element) {
        return (boolean) executeScript("""
                if (!arguments[0].getBoundingClientRect) {
                    return false
                }
                
                const rect = arguments[0].getBoundingClientRect()
                
                const windowHeight = (window.innerHeight || document.documentElement.clientHeight)
                const windowWidth = (window.innerWidth || document.documentElement.clientWidth)
                
                const vertInView = (rect.top <= windowHeight) && ((rect.top + rect.height) > 0)
                const horInView = (rect.left <= windowWidth) && ((rect.left + rect.width) > 0)
                
                return (vertInView && horInView)
                """, element);
    }

    public void scrollTo(WebElement element) {
        scrollTo(element, 1000);
    }

    public void scrollTo(Component element) {
        scrollTo(element.getParentElement());
    }

    public void scrollTo(Components<?> elements) {
        setTimeouts(driver, 2);
        while (elements.isEmpty()) {
            executeScript(String.format("""
                    window.scrollBy({
                      top: %d, // could be negative value
                      left: 0,
                    });
                    """, 1000), null);
        }
        waitForNumberOfElementsToBeMoreThan(elements, 0);
        scrollIntoCenter(elements.getFirst().getParentElement());
        setDefaultTimeouts(driver);
    }

    public void scrollIntoCenter(WebElement element) {
        executeScript("arguments[0].scrollIntoView({  block: \"center\", inline: \"nearest\" });", element);
    }

    public void scrollTo(WebElement element, int top) {
        log.trace("Scrolling to element: " + element);
        setTimeouts(driver, 1);
        while (!isDisplayed(element)) {
            executeScript(String.format("""
                    window.scrollBy({
                      top: %d, // could be negative value
                      left: 0,
                    });
                    """, top), null);
        }
        scrollIntoCenter(element);
        setDefaultTimeouts(driver);
    }


    public boolean isDisplayed(WebElement element) {
        log.trace("Checking if element is displayed: " + element);
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clear(WebElement element) {
        log.trace("Clearing element: " + element);
        Keys key = Keys.CONTROL;
        if (MAC.isCurrentOs()) {
            key = Keys.COMMAND;
        }
        element.sendKeys(Keys.chord(key, "a") + Keys.BACK_SPACE);
    }

    public void waitForNumberOfElements(By locator, int count) {
        wait.until(ExpectedConditions.numberOfElementsToBe(locator, count));
    }

    public void waitForNumberOfElementsToBeMoreThan(By locator, int count) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
    }

    public void waitForNumberOfElementsToBeMoreThan(Components<?> elements, int count) {
        wait.until(driver -> elements.size() > count);
    }

    public void waitForNumberOfElementsToBeMoreThan(List<?> elements, int count) {
        wait.until(driver -> elements.size() > count);
    }

    public void hover(WebElement element) {
        log.trace("Hovering over element: " + element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

}
