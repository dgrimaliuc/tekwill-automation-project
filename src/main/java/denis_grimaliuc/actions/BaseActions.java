package denis_grimaliuc.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static denis_grimaliuc.data.enums.OS.*;
import static helpers.Helpers.addQuotes;

public class BaseActions {

    static Logger log = Logger.getLogger(BaseActions.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
//    private final AdoptPage page;

    public BaseActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public static Object executeScript(WebDriver driver, String script, Object object) {
        return ((JavascriptExecutor) driver).executeScript(script, object);
    }


    public String clearShortcut() {
        Keys key;
        if (WINDOWS.isCurrentOs()) {
            key = Keys.CONTROL;
        } else if (MAC.isCurrentOs()) {
            key = Keys.COMMAND;
        } else {
            throw new NoSuchElementException("Unsupported OS type: " + addQuotes(getCurrentOS()));
        }

        return Keys.chord(key, "a") + Keys.BACK_SPACE;
    }


    public void hover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

}
