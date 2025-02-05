package denis_grimaliuc.actions;

import org.openqa.selenium.WebElement;

public class ShopifyActions {

    public static int convertPrice(WebElement price) {
        String priceText = price.getText();
        return Integer.parseInt(priceText.replace("$", "")
                .replace("Price: $", "")
                .replace("Total: $", ""));
    }
}
