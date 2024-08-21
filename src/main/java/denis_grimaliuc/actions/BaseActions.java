package denis_grimaliuc.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static denis_grimaliuc.data.enums.OS.MAC;

public class BaseActions {

    static Logger log = Logger.getLogger(BaseActions.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BaseActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Object executeScript(String script, Object object) {
        return ((JavascriptExecutor) driver).executeScript(script, object);
    }

    public void waitForNumberOfElements(List<WebElement> elements, int count) {
        log.trace("Waiting for number of elements: " + count);
        wait.until(driver -> elements.size() == count);
    }

    public void shouldHaveTextContains(WebElement element, String text) {
        log.trace("Checking if element has text: " + element);
        String actual = element.getText();
        wait.until(driver -> actual.contains(text));
    }

    public void shouldHaveTextEndsWith(WebElement element, String text) {
        log.trace("Checking if element has text: " + element);
        wait.until(driver -> element.getText().endsWith(text));
    }

    public void shouldSee(WebElement element) {
        log.trace("Checking if element is visible: " + element);
        try {
            wait.until(driver -> isInView(element));
        } catch (Exception e) {
            throw new TimeoutException("Element is not in viewport: " + element, e);
        }
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
        log.trace("Scrolling to element: " + element);
        executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void clear(WebElement element) {
        log.trace("Clearing element: " + element);
        Keys key = Keys.CONTROL;
        if (MAC.isCurrentOs()) {
            key = Keys.COMMAND;
        }
        element.sendKeys(Keys.chord(key, "a") + Keys.BACK_SPACE);
    }

    public void hover(WebElement element) {
        log.trace("Hovering over element: " + element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

}
