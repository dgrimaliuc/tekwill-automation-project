package denis_grimaliuc.ui.shopify.pages;

import org.openqa.selenium.By;

public class Shopify {

    public By cardContainer = By.id("card-container");
    public By firstItem = By.xpath("//div[@class='card'][1]");

    // Prices
    public By cardPrices = By.id("card-price");
    public By priceUnder25 = By.xpath("//input[@value='Under $25']");
    public By price25to50 = By.xpath("//input[@value='$25 to $50']");
    public By price50to100 = By.xpath("//input[@value='$50 to $100']");
    public By priceOver100 = By.xpath("//input[@value='Over $100']");

    // Colors
    public By cardColors = By.xpath("//p[@id='card-color']/canvas");
    // Sizes
    public By cardSizes = By.id("card-size");
    public By addToCart = By.id("add_to_cart_button");
    public By cartButton = By.className("cart-button");
    public By emptyCardTitle = By.className("empty-cart-title");
    public By plusButton = By.className("plus-item");
    public By minusButton = By.className("minus-item");
    public By itemQuantity = By.className("item-quantity");
    public By itemPrice = By.xpath("//p[contains(text(),'Price')]");
    public By cartItems = By.className("cart-card");
    public By totalPrice = By.className("total-price");

    // Genders
    public By cardGenders = By.id("card-gender");

    // Sorting dropdown
//    public By sortingIcon = By.id("dropdownIcon");
    public By sortingIcon = By.xpath("//div[i[@id='dropdownIcon']]");
    public By cardImages = By.id("card-image");

    public static By getColorByName(String color) {
        return By.xpath("//input[@value='" + color + "']");
    }

    public static By getSizeByName(String size) {
        return By.xpath("//input[@value='" + size + "']");
    }

    public static By getGenderByName(String gender) {
        return By.xpath("//input[@value='" + gender + "']");
    }

    public static By getSortingOption(String option) {
        return By.xpath("//ul[@id='dropdownList']/li[text()='" + option + "']");
    }
}
