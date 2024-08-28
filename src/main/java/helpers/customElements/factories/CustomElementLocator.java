package helpers.customElements.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

public class CustomElementLocator implements ElementLocator {

    private final SearchContext searchContext;
    private final By parentBy;
    private final WebElement parentElement;
    private final By by;


    public CustomElementLocator(SearchContext searchContext, Field field, By parentBy) {
        this(searchContext, new Annotations(field), null, parentBy);
    }

    public CustomElementLocator(SearchContext searchContext, Field field, WebElement parentElement) {
        this(searchContext, new Annotations(field), parentElement, null);
    }

    public CustomElementLocator(SearchContext searchContext, AbstractAnnotations annotations, WebElement parentElement, By parentBy) {
        this.searchContext = searchContext;
        this.by = annotations.buildBy();
        this.parentElement = parentElement;
        this.parentBy = parentBy;
    }

    public WebElement findElement() {
        if (this.parentElement != null) {
            return this.parentElement.findElement(this.by);
        } else if (this.parentBy != null) {
            WebElement parentEl = this.searchContext.findElement(parentBy);
            if (parentBy.equals(this.by)) {
                return parentEl;
            } else
                return parentEl.findElement(this.by);
        } else {
            return this.searchContext.findElement(this.by);
        }
    }

    public List<WebElement> findElements() {
        if (this.parentElement != null) {
            return this.parentElement.findElements(this.by);
        } else if (this.parentBy != null) {
            ByChained chained = new ByChained(parentBy, this.by);
            return this.searchContext.findElements(chained);
        } else {
            return this.searchContext.findElements(this.by);
        }
    }

    public String toString() {
        return this.getClass().getSimpleName() + " '" + this.by + "'";
    }
}
