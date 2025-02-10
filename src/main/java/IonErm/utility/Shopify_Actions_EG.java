package IonErm.utility;


import org.openqa.selenium.WebElement;

public class Shopify_Actions_EG {

    public static int convertPrice(WebElement price) {
        String priceText = price.getText();
        return Integer.parseInt(priceText.replace("$", "")
                .replace("Price: $", "")
                .replace("Total: $", ""));
    }
}
