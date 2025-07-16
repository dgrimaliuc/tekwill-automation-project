package helpers.customElements.factories;

import helpers.customElements.Component;
import helpers.customElements.Components;
import helpers.customElements.factories.handlers.DynamicWebElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.*;
import java.util.List;

public class CustomFieldDecorator implements FieldDecorator {
    protected LocatorFactory factory;

    public CustomFieldDecorator(LocatorFactory factory) {
        this.factory = factory;
    }

    public Object decorate(ClassLoader loader, Field field) {
        if (!this.isDecoratable(field)) {
            return null;
        } else {
            CustomElementLocator locator = getLocator(field);
            if (Component.class.isAssignableFrom(field.getType())) {
                return this.singleComponentObject(field, locator);
            } else if (WebElement.class.isAssignableFrom(field.getType())) {
                return this.proxyForLocator(loader, locator);
            } else if (Components.class.isAssignableFrom(field.getType())) {
                return this.proxyForComponentsLocator(field, locator);
            } else if (List.class.isAssignableFrom(field.getType())) {
                return this.proxyForListLocator(loader, locator);
            } else
                return null;
        }
    }

    private CustomElementLocator getLocator(Field field) {
        return this.factory.createLocator(field);
    }

    protected boolean isDecoratable(Field field) {
        if (WebElement.class.isAssignableFrom(field.getType())) {
            return true;
        } else if (Component.class.isAssignableFrom(field.getType())) {
            return true;
        } else if (Components.class.isAssignableFrom(field.getType())) {
            return true;
        } else if (List.class.isAssignableFrom(field.getType())) {
            return true;
        } else {
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return false;
            } else {
                Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
                if (!WebElement.class.equals(listType)) {
                    return false;
                } else {
                    return field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null || field.getAnnotation(FindAll.class) != null;
                }
            }
        }
    }

    protected WebElement proxyForLocator(ClassLoader loader, CustomElementLocator locator) {
        InvocationHandler handler = new DynamicWebElementHandler(locator);
        return (WebElement) Proxy.newProxyInstance(loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
    }

    protected List<WebElement> proxyForListLocator(ClassLoader loader, CustomElementLocator locator) {
        InvocationHandler handler = new LocatingElementListHandler(locator);
        return (List<WebElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }

    protected Object singleComponentObject(Field field, CustomElementLocator locator) {
        By parent = locator.locator;
        WebDriver driver = this.factory.getDriver();
        return ComponentProxyFactory.createLazyComponent(field, () -> new ComponentContext(driver, parent, locator.getIndex()));
    }

    protected Components<?> proxyForComponentsLocator(Field field, CustomElementLocator locator) {
        By parent = locator.locator;
        WebDriver driver = this.factory.getDriver();

        return ComponentProxyFactory.createLazyComponents(field, () -> new ComponentContext(driver, parent, null));
    }
}
