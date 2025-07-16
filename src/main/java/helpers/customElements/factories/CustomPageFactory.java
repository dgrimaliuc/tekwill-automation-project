package helpers.customElements.factories;

import helpers.customElements.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * If a WebElement found it tries to find element and to set its value
 * If a Component found it tries to create its object and to initialize its webElements
 */
public class CustomPageFactory {
    public static void initElements(WebDriver driver, Object page, ComponentContext context) {
        try {
            Class<?> clazz = page.getClass();
            Field[] elements = clazz.getFields();
            // Set every field
            for (Field element : elements) {
                String type = element.getType().getName();
                element.setAccessible(true);
                if (element.isAnnotationPresent(FindBy.class)) {
                    switch (type) {
                        case "java.util.List":
                        case "org.openqa.selenium.WebElement":
                        case "helpers.customElements.Components":
                            setField(driver, element, page, context);
                            break;
                        default:

                            if (Component.class.isAssignableFrom(element.getType())) {
                                ComponentContext tmpContext = null;
                                if (context == null) {
                                    tmpContext = new ComponentContext(driver, new Annotations(element).buildBy(), null);
                                }
                                setField(driver, element, page, context == null ? tmpContext : context);
                            } else if (element.get(page) == null)
                                throw new UnsupportedTemporalTypeException("Unsupported type of variable: " + element.getName() + " : " + type);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setField(WebDriver driver, Field field, Object page, ComponentContext context) throws IllegalAccessException {
        var factory = new LocatorFactory(driver, context);
        setField(field, page, factory);
    }

    private static void setField(Field field, Object page, LocatorFactory factory) throws IllegalAccessException {
        var decorator = new CustomFieldDecorator(factory);
        Object value = decorator.decorate(page.getClass().getClassLoader(), field);
        field.set(page, value);
    }
}
