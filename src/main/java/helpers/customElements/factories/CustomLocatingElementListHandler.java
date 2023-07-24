package helpers.customElements.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomLocatingElementListHandler implements InvocationHandler {
    private final CustomElementLocator locator;
    private final WebDriver driver;
    private final Class<?> componentClass;

    public CustomLocatingElementListHandler(WebDriver driver, CustomElementLocator locator, Class<?> componentClass) {
        this.driver = driver;
        this.locator = locator;
        this.componentClass = componentClass;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List elements = this.locator.findElements().stream().map();
        Object component = componentClass.getConstructor().newInstance();
        CustomPageFactory.initElements(driver, component, );

        try {
            return method.invoke(elements, objects);
        } catch (InvocationTargetException var6) {
            throw var6.getCause();
        }
    }

    public Object createComponentInstance(WebElement parent) {
        Object component = null;
        try {
            component = componentClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        CustomPageFactory.initElements(driver, component, parent);
    }
}
