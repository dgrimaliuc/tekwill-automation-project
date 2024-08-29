package helpers.customElements.factories;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class ClassProxyExample {

    public static void main(String[] args) throws Exception {
        MyClass myClassProxy = new ByteBuddy()
                .subclass(MyClass.class)
                .method(ElementMatchers.not(ElementMatchers.isDeclaredBy(Object.class)))
                .intercept(MethodDelegation.to(new MyInterceptor()))
                .make()
                .load(ClassProxyExample.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();

        myClassProxy.method1();
        myClassProxy.method2("Hello, ByteBuddy!");
    }

    public static class MyClass {  // Ensure this class is public
        public void method1() {
            System.out.println("Method1 executed");
        }

        public void method2(String message) {
            System.out.println("Method2 executed with message: " + message);
        }
    }

    public static class MyInterceptor {
        public void intercept() {
            System.out.println("Intercepted method call!");
        }
    }
}
