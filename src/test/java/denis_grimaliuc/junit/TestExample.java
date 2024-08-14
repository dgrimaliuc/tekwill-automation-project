package denis_grimaliuc.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;

import java.util.Properties;

import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE;
import static org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES;

class TestExample {

    private Properties backup;

    @BeforeAll
    static void initAll() {
        System.out.println("Before all test methods");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After all test methods");
    }

    @BeforeEach
    void backup() {
        backup = new Properties();
        backup.putAll(System.getProperties());
    }

    @AfterEach
    void restore() {
        System.setProperties(backup);
    }

    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ)
    void customPropertyIsNotSetByDefault() {
        Assertions.assertNull(System.getProperty("my.prop"));
    }

    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
    void canSetCustomPropertyToApple() {
        System.setProperty("my.prop", "apple");
        Assertions.assertEquals("apple", System.getProperty("my.prop"));
    }

    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
    void canSetCustomPropertyToBanana() {
        System.setProperty("my.prop", "banana");
        Assertions.assertEquals("banana", System.getProperty("my.prop"));
    }

    @Test
    @Tag("fast")
    @DisplayName("System out println test 1")
    public void test() {
        System.out.println("Test");
    }

    @Test
    @Tags({@Tag("fast"), @Tag("model")})
    @DisplayName("System out println test 2")
    public void test2() {
        System.out.println("Test");
    }

    @BeforeEach
    void init() {
        System.out.println("Before each test method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test method");
    }


}
