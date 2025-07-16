package helpers.customElements.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class LocatorFactory implements ElementLocatorFactory {
    private final WebDriver driver;
    private final By parent;
    private final Integer index;

    public LocatorFactory(WebDriver driver, ComponentContext context) {
        this.driver = driver;
        if (context == null) {
            this.parent = null;
            this.index = null;
        } else {
            this.parent = context.locator();
            this.index = context.index();
        }
    }

    public LocatorFactory(WebDriver driver, By parent) {
        this(driver, new ComponentContext(driver, parent, null));
    }

    public By getParent() {
        return this.parent;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public CustomElementLocator createLocator(Field field) {
        return new CustomElementLocator(this.driver, field, parent, index);
    }
}
