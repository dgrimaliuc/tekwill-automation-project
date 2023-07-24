package helpers.customElements.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class CustomElementLocatorFactory implements ElementLocatorFactory {
    private final By parent;
    private final WebDriver driver;
    
    public CustomElementLocatorFactory(WebDriver driver, By parent) {
        this.driver = driver;
        this.parent = parent;
    }

    public By getParent() {
        return this.parent;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public ElementLocator createLocator(Field field) {
        return new CustomElementLocator(this.driver, field, parent);
    }
}
