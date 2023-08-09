package helpers.customElements.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

import java.lang.reflect.Field;

public class CustomElementLocatorCOPY extends DefaultElementLocator {
    private final SearchContext searchContext;
    private final By by;
    private final By parent;


    public CustomElementLocatorCOPY(SearchContext searchContext, Field field) {
        this(searchContext, new Annotations(field), null);
    }

    public CustomElementLocatorCOPY(SearchContext searchContext, AbstractAnnotations annotations, By parent) {
        super(searchContext, annotations);
        this.searchContext = searchContext;
        this.by = annotations.buildBy();
        this.parent = parent;
    }
}
