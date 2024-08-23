package victor_murashev.ui.shopify;

import org.openqa.selenium.*;

import java.util.List;

public class Component implements WebElement {
    private final WebElement parent;


    public Component(WebElement parent) {
        this.parent = parent;
    }

    @Override
    public void click() {
        this.parent.click();
    }

    @Override
    public void submit() {
        this.parent.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        this.parent.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        this.parent.clear();
    }

    @Override
    public String getTagName() {
        return this.parent.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return this.parent.getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return this.parent.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return this.parent.isEnabled();
    }

    @Override
    public String getText() {
        return this.parent.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.parent.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.parent.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return this.parent.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return this.parent.getLocation();
    }

    @Override
    public Dimension getSize() {
        return this.parent.getSize();
    }

    @Override
    public Rectangle getRect() {
        return this.parent.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return this.parent.getCssValue(s);
    }


    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return this.parent.getScreenshotAs(outputType);
    }
}
