package denis_grimaliuc.junit;

import denis_grimaliuc.shopify.ShopifyPage;
import internal.BaseTest;
import org.junit.jupiter.api.Test;

public class ShopifyJunitTests extends BaseTest {

    ShopifyPage shopifyPage = new ShopifyPage(driver);

    @Test
    public void openShopifyPage() {
        log.info("Opening the Shopify page");
        driver.get("https://shopify-eta-drab.vercel.app/#");
        shopifyPage.addToCartButtons.getFirst().click();

        String title = shopifyPage.titles.getFirst().getText();
        String color = shopifyPage.colors.getFirst().getAttribute("data-t");
        String price = shopifyPage.prices.getFirst().getText();
        String size = shopifyPage.sizes.getFirst().getText();
        String image = shopifyPage.images.getFirst().getAttribute("src");

        shopifyPage.cartButton.click();

        actions.shouldHaveAttribute(shopifyPage.cartItemImage, "src", image);
        actions.shouldHaveTextToBe(shopifyPage.cartItemTitle, title);
        actions.shouldHaveTextToBe(shopifyPage.cartItemPrice, price);
        actions.shouldHaveTextToBe(shopifyPage.cartItemColor, color);
        actions.shouldHaveTextToBe(shopifyPage.cartItemSize, size);
        actions.shouldHaveTextToBe(shopifyPage.cartTotalPrice, price);
        actions.shouldHaveTextToBe(shopifyPage.cartItemQuantity, "1");


    }

}
