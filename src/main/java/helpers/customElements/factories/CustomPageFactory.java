package helpers.customElements.factories;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * If a WebElement found it tries to find element and to set its value
 * If a Component found it tries to create its object and to initialize its webElements
 */
public class CustomPageFactory {
    public static void main(String[] args) {
        System.out.println();
    }

    public static void initElements(WebDriver driver, Object page, By parent) {
        try {
            Class<?> clazz = page.getClass();
            Field[] elements = clazz.getFields();
            // Set every WebElement field
            for (Field element : elements) {
                String type = element.getType().getName();
                element.setAccessible(true);
                if (element.isAnnotationPresent(FindBy.class)) {
                    var initPath = getInitPath(element);

                    switch (type) {
                        case "helpers.customElements.Components":
                        case "org.openqa.selenium.WebElement":
                        case "java.util.List":
                            setProxyLocator(driver, element, page, parent);


//                            if (element.isAnnotationPresent(ComponentType.class)) {
//                                Class<?> componentClass = element.getAnnotation(ComponentType.class).value();
//                                waitIfPresent(driver, initPath);
//                                List<Object> list = driver.findElements(initPath).stream()
//                                        .map(parent -> createComponentInstance(componentClass, driver, parent))
//                                        .collect(Collectors.toList());
//                                element.set(page, new Components<Adoption>(list));
//                            } else
//                                throw new NoSuchElementException("ComponentType annotation is absent for a Components element");
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

    public static void initElements(WebDriver driver, Object page, WebElement parent) {

    }

    private static Object createComponentInstance(Class<?> componentClass, WebDriver driver, WebElement parent) {
        try {
            Constructor<?> constructor = componentClass.getDeclaredConstructor(WebDriver.class, WebElement.class);
            return constructor.newInstance(driver, parent);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
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
}
