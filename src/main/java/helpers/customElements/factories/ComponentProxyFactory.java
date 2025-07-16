package helpers.customElements.factories;

import helpers.customElements.Component;
import helpers.customElements.Components;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.openqa.selenium.WebElement;

import java.lang.reflect.*;
import java.util.List;
import java.util.function.Supplier;

public class ComponentProxyFactory {
    public static <T extends WebElement> T createLazyComponent(Field field, Supplier<ComponentContext> contextSupplier) {
        Class<T> clazz = (Class<T>) field.getType();
        return createLazyComponent(clazz, contextSupplier);
    }

    public static <T extends Component> Components<T> createLazyComponents(Field field, Supplier<ComponentContext> contextSupplier) {
        Class<T> clazz = (Class<T>) getGenericType(field);
        return createLazyComponents(clazz, contextSupplier);
    }

    public static <T extends WebElement> T createLazyComponent(Class<T> clazz, Supplier<ComponentContext> contextSupplier) {

        try {
            Class<? extends T> proxyType = new ByteBuddy()
                    .subclass(clazz)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(new LazyComponentInterceptor<>(clazz, contextSupplier)))
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("LazyProxy(" + clazz.getSimpleName() + ")"))
                    .make()
                    .load(clazz.getClassLoader())
                    .getLoaded();

            T proxy = InstanceFactory.allocate(proxyType);
            CustomPageFactory.initElements(contextSupplier.get().driver(), proxy, contextSupplier.get());
            return proxy;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create proxy for: " + clazz, e);
        }
    }

    public static <T extends Component> Components<T> createLazyComponents(
            Class<T> clazz,
            Supplier<ComponentContext> contextSupplier
    ) {
        InvocationHandler handler = new LazyComponentsListHandler<>(clazz, contextSupplier);

        List<T> proxyList = (List<T>) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{List.class},
                handler
        );

        return new Components<>(proxyList);
    }

    private static Class<?> getGenericType(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType parameterizedType) {
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments.length > 0 && typeArguments[0] instanceof Class) {
                return (Class<?>) typeArguments[0];
            }
        }
        return null;
    }
}
