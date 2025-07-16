package helpers.customElements.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public record ComponentContext(WebDriver driver, By locator, Integer index) {

    public static ComponentContext of(WebDriver driver, By locator) {
        return new ComponentContext(driver, locator, null);
    }

    public static ComponentContext of(WebDriver driver, By locator, Integer index) {
        return new ComponentContext(driver, locator, index);
    }
}
