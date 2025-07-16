package helpers.customElements.factories;

import net.bytebuddy.implementation.bind.annotation.*;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class LazyComponentInterceptor<T extends WebElement> {
    private final Class<T> componentClass;
    private final Supplier<ComponentContext> contextSupplier;

    public LazyComponentInterceptor(Class<T> componentClass, Supplier<ComponentContext> contextSupplier) {
        this.componentClass = componentClass;
        this.contextSupplier = contextSupplier;
    }

    @RuntimeType
    public Object intercept(@Origin Method method,
                            @AllArguments Object[] args,
                            @SuperCall Callable<Object> superCall,
                            @This Object proxy) throws Throwable {

        T instance = createComponent();
        return method.invoke(instance, args);
    }

    private T createComponent() throws Exception {
        ComponentContext context = contextSupplier.get();
        Constructor<T> constructor = componentClass.getDeclaredConstructor(ComponentContext.class);
        constructor.setAccessible(true);
        T instance = constructor.newInstance(context);

        CustomPageFactory.initElements(context.driver(), instance, context);
        return instance;
    }
}
