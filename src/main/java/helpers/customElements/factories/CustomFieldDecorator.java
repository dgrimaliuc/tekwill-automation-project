package helpers.customElements.factories;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.*;
import java.util.List;

public class CustomFieldDecorator implements FieldDecorator {
    protected CustomElementLocatorFactory factory;

    public CustomFieldDecorator(CustomElementLocatorFactory factory) {
        this.factory = factory;
    }

    public Object decorate(ClassLoader loader, Field field) {

        if (!this.isDecoratable(field)) {
            return null;
        } else {
            CustomElementLocator locator = getLocator(field);
            if (Component.class.isAssignableFrom(field.getType())) {
                return this.singleComponentObject(field, loader, locator);
            } else if (WebElement.class.isAssignableFrom(field.getType())) {
                return this.proxyForLocator(loader, locator);
            } else if (Components.class.isAssignableFrom(field.getType())) {
                return this.proxyForComponentsLocator(field, loader, locator);
            } else if (List.class.isAssignableFrom(field.getType())) {
                return this.proxyForListLocator(loader, locator);
            } else
                return null;
        }
    }

    private CustomElementLocator getLocator(Field field) {
        if (this.factory.getParentElement() == null) {
            return this.factory.createLocator(field);
        } else {
            return this.factory.createComponentsLocator(field);
        }
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
        InvocationHandler handler = new LocatingElementHandler(locator);
        return (WebElement) Proxy.newProxyInstance(loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
    }

    protected List<WebElement> proxyForListLocator(ClassLoader loader, CustomElementLocator locator) {
        InvocationHandler handler = new LocatingElementListHandler(locator);
        return (List<WebElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }

    protected Object singleComponentObject(Field field, ClassLoader loader, CustomElementLocator locator) {
        try {
            WebElement parentElement = this.proxyForLocator(loader, locator);
            By parent = this.factory.getParent();
            Object component = field.getType().getDeclaredConstructor(WebElement.class).newInstance(parentElement);
            WebDriver driver = this.factory.getDriver();
            CustomPageFactory.initElements(driver, component, parent);
            return component;

        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    protected Components<?> proxyForComponentsLocator(Field field, ClassLoader loader, CustomElementLocator locator) {
        Class<?> componentType = ((Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);
        InvocationHandler handler = new LocatingElementComponentsHandler(this.factory.getDriver(), locator, componentType);
        List<?> proxy = (List<?>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return new Components<>(proxy);
    }
}
