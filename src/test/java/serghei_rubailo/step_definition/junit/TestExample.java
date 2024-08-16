package serghei_rubailo.step_definition.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestExample {

    @BeforeAll
    static void beforeInit() {
        System.out.println("Before All");
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {1,2,3,4,5,6})
    @DisplayName("The first test!")
    @Tags({@Tag("first"), @Tag("model")})
    public void test1(Integer actual) {
        assertThat(actual > 0, equalTo(true));
    }

    @Test
    @DisplayName("The first test!")
    @Tags({@Tag("first"), @Tag("second")})
    public void  test2() {
        System.out.println("Hi2");
    }

    @BeforeEach
    void init() {
        System.out.println("Start");
    }

    @AfterEach
    void close() {
        System.out.println("End");
    }


    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }

    @Test
    @DisplayName("Simple Test")
    void simpleTest() {
        assertThat(2+3, equalTo(5));
    }


}
