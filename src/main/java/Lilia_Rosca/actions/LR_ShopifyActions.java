package Lilia_Rosca.actions;

import org.openqa.selenium.WebElement;

public class LR_ShopifyActions {
// 03.02
    public static int convertPrice(WebElement price) {
        String priceText = price.getText();
        return Integer.parseInt(priceText.replace("Price: $", "")
                .replace("Total: $", "")
                .replace("$", ""));
    }
}