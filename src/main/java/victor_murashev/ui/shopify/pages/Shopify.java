package victor_murashev.ui.shopify.pages;

import org.openqa.selenium.By;

public class Shopify {

    public By cardContainer = By.cssSelector("#card-container");
    public By firstItem = By.cssSelector(".card:nth-of-type(1)");

    // Prices
    public By cardPrices = By.cssSelector("#card-price");
    public By priceUnder25 = By.cssSelector("input[value='Under $25']");
    public By price25to50 = By.cssSelector("input[value='$25 to $50']");
    public By price50to100 = By.cssSelector("input[value='$50 to $100']");
    public By priceOver100 = By.cssSelector("input[value='Over $100']");

    // Colors
    public By cardColors = By.cssSelector("#card-color > canvas");
    // Sizes
    public By cardSizes = By.cssSelector("#card-size");
    public By addToCart = By.cssSelector("#add_to_cart_button");
    public By cartButton = By.cssSelector(".cart-button");
    public By emptyCardTitle = By.cssSelector(".empty-cart-title");
    public By plusButton = By.cssSelector(".plus-item");
    public By minusButton = By.cssSelector(".minus-item");
    public By itemQuantity = By.cssSelector(".item-quantity");
    public By itemPrice = By.xpath("//p[contains(text(),'Price')]");
    public By cartItems = By.cssSelector(".cart-card");
    public By totalPrice = By.cssSelector(".total-price");

    // Genders
    public By cardGenders = By.cssSelector("#card-gender");

    // Sorting dropdown
//    public By sortingIcon = By.id("dropdownIcon");
    public By sortingButton = By.cssSelector("#dropdownButton");
    public By cardImages = By.cssSelector("#card-image");

    public static By getColorByName(String color) {
        return By.cssSelector("input[value='" + color + "']");
    }

    public static By getSizeByName(String size) {
        return By.cssSelector("input[value='" + size + "']");
    }

    public static By getGenderByName(String gender) {
        return By.cssSelector("input[value='" + gender + "']");
    }

    public static By getSortingOption(String option) {
        return By.xpath("//ul[@id='dropdownList']/li[text()='" + option + "']");
    }
}
