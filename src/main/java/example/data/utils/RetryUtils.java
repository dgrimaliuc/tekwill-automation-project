package example.data.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.function.Supplier;

import static example.actions.BaseActions.waitForMills;

public class RetryUtils {
    public static <T> T retry(Supplier<T> action, int attempts) {
        Throwable lastException = null;

        for (int i = 0; i < attempts; i++) {
            try {
                return action.get();
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                lastException = e;
                System.out.println("Retrying after: " + e.getClass().getSimpleName());
                waitForMills(300);
            }
        }

        throw new RuntimeException("Failed after " + attempts + " attempts", lastException);
    }
}
