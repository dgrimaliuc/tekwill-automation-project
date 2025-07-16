package helpers.customElements.factories;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class InstanceFactory {
    private static final Unsafe unsafe;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to access Unsafe", e);
        }
    }

    public static <T> T allocate(Class<T> clazz) {
        try {
            return (T) unsafe.allocateInstance(clazz);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to allocate instance of " + clazz, e);
        }
    }
}
