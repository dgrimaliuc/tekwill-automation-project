package helpers.customElements.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class CustomLocatingElementListHandler implements InvocationHandler {
    private final CustomElementLocator locator;
    private final WebDriver driver;
    private final Class<?> componentType;

    public CustomLocatingElementListHandler(WebDriver driver, CustomElementLocator locator, Class<?> componentClass) {
        this.driver = driver;
        this.locator = locator;
        this.componentType = componentClass;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<?> elements = this.locator.findElements().stream()
                .map(this::createComponentInstance).collect(Collectors.toList());
        try {
            return method.invoke(elements, objects);
        } catch (InvocationTargetException var6) {
            throw var6.getCause();
        }
    }

    public Object createComponentInstance(WebElement parent) {
        try {
            Object component = componentType.getConstructor().newInstance();
            CustomPageFactory.initComponent(driver, component, parent);
            return component;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
