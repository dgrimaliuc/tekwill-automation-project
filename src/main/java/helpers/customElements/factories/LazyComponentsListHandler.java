package helpers.customElements.factories;

import helpers.customElements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class LazyComponentsListHandler<T extends Component> implements InvocationHandler {
    private final Class<T> componentClass;
    private final Supplier<ComponentContext> contextSupplier;

    public LazyComponentsListHandler(Class<T> componentClass, Supplier<ComponentContext> contextSupplier) {
        this.componentClass = componentClass;
        this.contextSupplier = contextSupplier;
    }

    private List<T> createComponents(WebDriver driver, By selfLocator) {
        return IntStream.range(0, driver.findElements(selfLocator).size())
                .boxed()
                .map(index -> proxiedComponent(driver, selfLocator, index))
                .toList();
    }

    private T proxiedComponent(WebDriver driver, By selfLocator, Integer index) {
        return ComponentProxyFactory.createLazyComponent(componentClass, () -> new ComponentContext(driver, selfLocator, index));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ComponentContext ctx = contextSupplier.get();
        WebDriver driver = ctx.driver();
        By locator = ctx.locator();

        List<T> fresh = createComponents(driver, locator);
        return method.invoke(fresh, args);
    }
}
