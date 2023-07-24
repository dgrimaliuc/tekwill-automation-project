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
import org.openqa.selenium.support.pagefactory.ElementLocator;
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
        try {
            if (!this.isDecoratable(field)) {
                return null;
            } else {
                ElementLocator locator = this.factory.createLocator(field);
                if (locator == null) {
                    return null;
                } else if (WebElement.class.isAssignableFrom(field.getType())) {
                    return this.proxyForLocator(loader, locator);
                } else if (Components.class.isAssignableFrom(field.getType())) {
                    Type genericType = field.getGenericType();
                    Type componentItemType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
                    By parent = this.factory.getParent();
                    Object component = field.getType().getDeclaredConstructor().newInstance();
                    WebDriver driver = this.factory.getDriver();
                    CustomPageFactory.initElements(driver, component, parent);
                    return component;

                } else if (List.class.isAssignableFrom(field.getType())) {
                    return this.proxyForListLocator(loader, locator);
                } else if (Component.class.isAssignableFrom(field.getType())) {
                    By parent = this.factory.getParent();
                    Object component = field.getType().getDeclaredConstructor().newInstance();
                    WebDriver driver = this.factory.getDriver();
                    CustomPageFactory.initElements(driver, component, parent);
                    return component;
                } else
                    return null;
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
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

    protected WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementHandler(locator);
        WebElement proxy = (WebElement) Proxy.newProxyInstance(loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
        return proxy;
    }

    protected List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementListHandler(locator);
        List<WebElement> proxy = (List) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }

    protected List<WebElement> proxyForComponentsLocator(ClassLoader loader, ElementLocator locator, WebDriver driver) {
        InvocationHandler handler = new LocatingElementListHandler(locator);
        List<WebElement> proxy = (List) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }
}
