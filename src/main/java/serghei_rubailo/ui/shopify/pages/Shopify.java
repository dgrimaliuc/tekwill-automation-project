package serghei_rubailo.ui.shopify.pages;

import org.openqa.selenium.By;

public class Shopify {
//Price
    public By priceUnder25 = By.xpath("//input[@value='Under $25']");
    public By price25to50 = By.xpath("//input[@value='$25 to $50']");
    public By price50to100 = By.xpath("//input[@value='$50 to $100']");
    public By priceOver100 = By.xpath("//input[@value='Over $100']");

    public By cardPrice = By.xpath("//*[@id='card-price']");

//Color
    public By getColorByName(String color) {
        return By.xpath("//input[@value='" + color + "']");
    }

    public By cardColor = By.xpath("//p[@id='card-color']/canvas");

//Size
    public By getSizeByName(String name) {
        return By.xpath("//input[@value='" + name + "']");
    }

    public By cardSize = By.xpath("//p[@id='card-size']");

//Gender
    public By getGenderByName(String gender) {
        return By.xpath("//input[@value='" + gender + "']");
    }

    public By cardGender = By.xpath("//p[@id='card-gender']");

//Nothing to show
    public By nothingToShowMessage = By.xpath("//*[@id='card-container']");

//Sort filter
    public By pageFilter = By.xpath("//*[@id='dropdownButton']");

    public By getSortFilterOption(String option) {
        return By.xpath("//*[@id='dropdownList']/li[text()='" + option + "']");
    }

//Card
    public By card = By.className("card");
    public By cardTitle = By.id("card-title");
    public By cardImage = By.id("card-image");
    public By cardAddButton = By.id("add_to_cart_button");

//Cart
    public By cartButton = By.className("cart-button");
    public By cartItems = By.className("cart-card");

//CartItem
    public By cartItemTitle = By.tagName("h4");
    public By cartItemPrice = By.xpath("//p[contains(text(), 'Price')]");
    public By cartItemColor = By.xpath("//p[contains(text(), 'Color')]");
    public By cartItemSize = By.xpath("//p[contains(text(), 'Size')]");
    public By cartItemImage = By.className("cart-card-image");
    public By cartItemPlusButton = By.className("plus-item");
    public By cartItemMinusButton = By.className("minus-item");
    public By cartItemQuantity = By.className("item-quantity");
    public By cartTotalPrice = By.className("total-price");
    public By cartEmptyCartTitle = By.className("empty-cart-title");


}
