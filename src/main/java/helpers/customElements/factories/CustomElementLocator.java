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
    final By parent;
    final By locator;
    final Integer index;
    private final SearchContext searchContext;

    public CustomElementLocator(SearchContext searchContext, Field field, By parentBy, Integer index) {
        this(searchContext, new Annotations(field), parentBy, index);
    }

    public CustomElementLocator(SearchContext searchContext, AbstractAnnotations annotations, By parentBy, Integer index) {
        this.searchContext = searchContext;
        this.locator = annotations.buildBy();
        this.parent = parentBy;
        this.index = index;
    }

    public WebElement findElement() {
        // TODO if there is cases then parent is null
        if (this.parent != null) {
            WebElement parentEl = this.searchContext.findElement(this.parent);
            if (parent.equals(this.locator)) {
                return parentEl;
            } else
                return parentEl.findElement(this.locator);
        } else {
            return this.searchContext.findElement(this.locator);
        }
    }

    public List<WebElement> findElements() {
        WebElement parentEl = null;
        if (parent != null) {
            parentEl = this.searchContext.findElement(this.parent);
            if (parent.equals(this.locator)) {
                return List.of(parentEl);
            }
        }

        return parentEl.findElements(this.locator);

//        return retry(() -> searchContext.findElements(effectiveLocator), 2);
    }
//
//    public static <T> T retry(Supplier<T> action, int attempts) {
//        for (int i = 0; i < attempts; i++) {
//            try {
//                return action.get();
//            } catch (StaleElementReferenceException e) {
//                System.out.println("StaleElementReferenceException caught, retrying... Attempt " + (i + 1));
//            }
//        }
//        throw new StaleElementReferenceException("Max retries exceeded");
//    }

    public Integer getIndex() {
        return this.index;
    }

    public String toString() {
        return this.getClass().getSimpleName() + " '" + this.locator + "'";
    }
}
