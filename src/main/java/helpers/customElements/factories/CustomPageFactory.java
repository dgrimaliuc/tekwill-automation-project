package helpers.customElements.factories;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * If a WebElement found it tries to find element and to set its value
 * If a Component found it tries to create its object and to initialize its webElements
 */
public class CustomPageFactory {

    public static void initElements(WebDriver driver, Object page, By parent) {
        try {
            Class<?> clazz = page.getClass();
            Field[] elements = clazz.getFields();
            // Set every WebElement field
            for (Field element : elements) {
                String type = element.getType().getName();
                element.setAccessible(true);
                if (element.isAnnotationPresent(FindBy.class)) {
                    switch (type) {
                        case "java.util.List":
                        case "org.openqa.selenium.WebElement":
                        case "helpers.customElements.Components":
                            setProxyLocator(driver, element, page, parent);
                            break;
                        default:
                            if (Component.class.isAssignableFrom(element.getType())) {
                                var parentBy = getInitPath(element);
                                setProxyLocator(driver, element, page, parentBy);
                            } else if (element.get(page) == null)
                                throw new UnsupportedTemporalTypeException("Unsupported type of variable: " + element.getName() + " : " + type);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initComponent(WebDriver driver, Object page, WebElement parent) {
        try {
            Class<?> clazz = page.getClass();
            Field[] elements = clazz.getFields();
            // Set every Component field for Components<?>
            for (Field element : elements) {
                String type = element.getType().getName();
                element.setAccessible(true);
                if (element.isAnnotationPresent(FindBy.class)) {
                    if (Component.class.isAssignableFrom(element.getType())) {
                        setProxyLocator(driver, element, page, parent);
                    } else {
                        switch (type) {
                            case "java.util.List":
                            case "org.openqa.selenium.WebElement":
                            case "helpers.customElements.Components":
                                setProxyLocator(driver, element, page, parent);
                                break;
                            default:
                                if (element.get(page) == null)
                                    throw new UnsupportedTemporalTypeException("Unsupported type of variable: " + element.getName() + " : " + type);
                                break;
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static By getInitPath(Field field) {
        if (field.isAnnotationPresent(FindBy.class)) {
            var findBy = field.getDeclaredAnnotation(FindBy.class);
            return new FindBy.FindByBuilder().buildIt(findBy, field);
        } else
            return null;
    }

    private static void setProxyLocator(WebDriver driver, Field field, Object page, By parent) throws IllegalAccessException {
        var factory = new CustomElementLocatorFactory(driver, parent);
        var decorator = new CustomFieldDecorator(factory);
        Object value = decorator.decorate(page.getClass().getClassLoader(), field);
        field.set(page, value);
    }

    private static void setProxyLocator(WebDriver driver, Field field, Object page, WebElement parent) throws IllegalAccessException {
        var factory = new CustomElementLocatorFactory(driver, parent);
        var decorator = new CustomFieldDecorator(factory);
        Object value = decorator.decorate(page.getClass().getClassLoader(), field);
        field.set(page, value);
    }
}
