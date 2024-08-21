package serghei_rubailo.ui.shopify.pages;

import org.openqa.selenium.By;

public class Shopify {
//Price
    public By priceUnder25 = By.cssSelector("input[value='Under $25']");
    public By price25to50 = By.cssSelector("input[value='$25 to $50']");
    public By price50to100 = By.cssSelector("input[value='$50 to $100']");
    public By priceOver100 = By.cssSelector("input[value='Over $100']");

    public By cardPrice = By.cssSelector("[id='card-price']");

//Color
    public By getColorByName(String color) {
        return By.cssSelector("input[value='" + color + "']");
    }

    public By cardColor = By.cssSelector("p[id='card-color'] > canvas");

//Size
    public By getSizeByName(String name) {
        return By.cssSelector("input[value='" + name + "']");
    }

    public By cardSize = By.cssSelector("[id='card-size']");

//Gender
    public By getGenderByName(String gender) {
        return By.cssSelector("input[value='" + gender + "']");
    }

    public By cardGender = By.cssSelector("[id='card-gender']");

//Nothing to show
    public By nothingToShowMessage = By.cssSelector("[id='card-container']");

//Sort filter
    public By pageFilter = By.cssSelector("[id='dropdownButton']");

    public By getSortFilterOption(String option) {
        return By.xpath("//*[@id='dropdownList']/li[text()='" + option + "']");
    }

//Card
    public By card = By.cssSelector(".card");
    public By cardTitle = By.id("card-title");
    public By cardImage = By.id("card-image");
    public By cardAddButton = By.id("add_to_cart_button");

//Cart
    public By cartButton = By.cssSelector(".cart-button");
    public By cartItems = By.cssSelector(".cart-card");

//CartItem
    public By cartItemTitle = By.tagName("h4");
    public By cartItemPrice = By.xpath("//p[contains(text(), 'Price')]");
    public By cartItemColor = By.xpath("//p[contains(text(), 'Color')]");
    public By cartItemSize = By.xpath("//p[contains(text(), 'Size')]");
    public By cartItemImage = By.cssSelector(".cart-card-image");
    public By cartItemPlusButton = By.cssSelector(".plus-item");
    public By cartItemMinusButton = By.cssSelector(".minus-item");
    public By cartItemQuantity = By.cssSelector(".item-quantity");
    public By cartTotalPrice = By.cssSelector(".total-price");
    public By cartEmptyCartTitle = By.cssSelector(".empty-cart-title");
    public By cartItemRemoveButton = By.cssSelector(".remove-item");


}
