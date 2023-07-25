package helpers.customElements.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

public class CustomElementLocator implements ElementLocator {

    private final SearchContext searchContext;
    private final By parent;
    private final WebElement parentElement;
    private final By by;


    public CustomElementLocator(SearchContext searchContext, Field field, By parent) {
        this(searchContext, new Annotations(field), null, parent);
    }

    public CustomElementLocator(SearchContext searchContext, Field field, WebElement parentElement) {
        this(searchContext, new Annotations(field), parentElement, null);
    }

    public CustomElementLocator(SearchContext searchContext, AbstractAnnotations annotations, WebElement parentElement, By parent) {
        this.searchContext = searchContext;
        this.by = annotations.buildBy();
        this.parentElement = parentElement;
        this.parent = parent;
    }

    public WebElement findElement() {
        if (this.parent == null) {
            return this.searchContext.findElement(this.by);
        } else {
            WebElement parentEl = this.searchContext.findElement(parent);
            return parentEl.findElement(this.by);
        }
    }

    public List<WebElement> findComponents() {
        return parentElement.findElements(this.by);
    }

    public List<WebElement> findElements() {
        if (this.parent == null) {
            return this.searchContext.findElements(this.by);
        } else {
            WebElement parentEl = this.searchContext.findElement(parent);
            return parentEl.findElements(this.by);
        }
    }

    public By getLocator() {
        return by;
    }

    public String toString() {
        return this.getClass().getSimpleName() + " '" + this.by + "'";
    }
}
