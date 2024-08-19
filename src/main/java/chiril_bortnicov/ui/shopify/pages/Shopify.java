package chiril_bortnicov.ui.shopify.pages;

import org.openqa.selenium.By;

public class Shopify {

    public By cardContainer = By.id("card-container");

    public By firstItem = By.xpath("//div[@class='card'][1]");

    //Prices
    public By cardPrices = By.id("card-price");
    public By priceUnder25 = By.xpath("//input[@value='Under $25']");
    public By price25to50 = By.xpath("//input[@value='$25 to $50']");
    public By price50to100 = By.xpath("//input[@value='$50 to $100']");
    public By priceOver100 = By.xpath("//input[@value='Over $100']");

    //Color
    public By cardColors = By.xpath("//p[@id='card-color']/canvas");

    public static By getColorByName (String color) {
        return By.xpath("//input[@value='" + color + "']");
    }

    //Size

    public By cardSizes = By.id("card-size");

    public static By getSizeByName (String size) {
        return By.xpath("//input[@value='" + size + "']");
    }

    //Gender

    public By cardGenders = By.id("card-gender");

    public static By getGenderByName (String gender) {
        return By.xpath("//input[@value='" + gender + "']");
    }

    //Sorting

    public By sortingButton = By.id("dropdownButton");
    public By emptyCartTitle = By.className("empty-cart-title");
    public By addToCart = By.id("add_to_cart_button");
    public By plusButton = By.className("plus-item");
    public By removeButton = By.className("remove-item");
    public By minusButton = By.className("minus-item");
    public By itemQuantity = By.className("item-quantity");
    public By cartButton = By.className("cart-button");
    public By cartItems = By.className("cart-card");
    public By totalPrice = By.className("total-price");
    public By itemPrice = By.xpath("//p[contains(text(),'Price')]");
    public By cartWrapper = By.className("cart-cards-wrapper");
    public By orderButton = By.className("order-button");


    public static By getSortingOption(String option) {
        return By.xpath("//ul[@id='dropdownList']/li[text()='" + option + "']");
    }


}
