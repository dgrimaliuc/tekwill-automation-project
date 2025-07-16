package helpers.customElements.factories.handlers;

import helpers.customElements.factories.CustomElementLocator;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class DynamicWebElementHandler implements InvocationHandler {

    private final CustomElementLocator locator;

    public DynamicWebElementHandler(CustomElementLocator locator) {
        this.locator = locator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Специальный случай: метод toString()
        if ("toString".equals(method.getName())) {
            return "Proxy element for: " + locator;
        }

        // Специальный случай: метод getWrappedElement()
        if ("getWrappedElement".equals(method.getName())) {
            return resolveElement();
        }

        System.out.println("Invoking method: " + method.getName() + " on proxy for: " + locator);

        WebElement element = resolveElement();

        try {
            return method.invoke(element, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    private WebElement resolveElement() {
        if (locator.getIndex() != null) {
            return getElementAtIndex(locator.getIndex());
        } else {
            return locator.findElement();
        }
    }

    private WebElement getElementAtIndex(int index) {
        List<WebElement> elements = locator.findElements();
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for elements: " + locator);
        }
        return elements.get(index);
    }
}
