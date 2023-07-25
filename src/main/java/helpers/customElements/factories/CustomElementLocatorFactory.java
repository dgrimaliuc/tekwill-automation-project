package helpers.customElements.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class CustomElementLocatorFactory implements ElementLocatorFactory {
    private final By parent;
    private final WebElement parentElement;
    private final WebDriver driver;

    public CustomElementLocatorFactory(WebDriver driver, By parent) {
        this.driver = driver;
        this.parent = parent;
        this.parentElement = null;
    }

    public CustomElementLocatorFactory(WebDriver driver, WebElement parentElement) {
        this.driver = driver;
        this.parentElement = parentElement;
        this.parent = null;
    }

    public By getParent() {
        return this.parent;
    }

    public WebElement getParentElement() {
        return this.parentElement;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public CustomElementLocator createLocator(Field field) {
        return new CustomElementLocator(this.driver, field, parent);
    }

    public CustomElementLocator createComponentsLocator(Field field) {
        return new CustomElementLocator(this.driver, field, parentElement);
    }
}
