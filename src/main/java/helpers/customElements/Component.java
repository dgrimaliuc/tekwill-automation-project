package helpers.customElements;

import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.*;

import java.util.List;

public class Component implements WebElement {
    public final ComponentContext context;

    public Component(ComponentContext context) {
        this.context = context;
    }

    protected WebDriver driver() {
        return context.driver();
    }

    public By parentLocator() {
        return context.locator();
    }

    protected Integer index() {
        return context.index();
    }

    public WebElement findParent() {
        List<WebElement> parents = driver().findElements(parentLocator());
        if (index() == null) {
            if (parents.isEmpty())
                throw new NoSuchElementException(parentLocator().toString());
            return parents.getFirst();
        }

        if (index() < parents.size())
            return parents.get(index());

        throw new NoSuchElementException("Index " + index() + " for " + parentLocator());
    }

    @Override
    public void click() {
        this.findParent().click();
    }

    @Override
    public void submit() {
        this.findParent().submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        this.findParent().sendKeys(charSequences);
    }

    @Override
    public void clear() {
        this.findParent().clear();
    }

    @Override
    public String getTagName() {
        return this.findParent().getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return this.findParent().getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return this.findParent().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return this.findParent().isEnabled();
    }

    @Override
    public String getText() {
        return this.findParent().getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.findParent().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.findParent().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return this.findParent().isDisplayed();
    }

    public boolean isPresent() {
        try {
            this.findParent();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public Point getLocation() {
        return this.findParent().getLocation();
    }

    @Override
    public Dimension getSize() {
        return this.findParent().getSize();
    }

    @Override
    public Rectangle getRect() {
        return this.findParent().getRect();
    }

    @Override
    public String getCssValue(String s) {
        return this.findParent().getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return this.findParent().getScreenshotAs(outputType);
    }
}
