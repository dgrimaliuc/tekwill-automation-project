package PotoracMihai.ui.shopify.pages;

import org.openqa.selenium.By;

public class ShopifyTask3 {
    public By cardContainer = By.id("card-container");
    //Price
    public By priceUnder25 = By.xpath("//input[@value='Under $25']");
    public By price25to50 = By.xpath("//input[@value='$25 to $50']");
    public By price50to100 = By.xpath("//input[@value='$50 to $100']");
    public By priceOver100 = By.xpath("//input[@value='Over $100']");

    public By cardPrices = By.xpath("//*[@id='card-price']");

    //Color
    public static By getColorByName(String color) {
        return By.xpath("//input[@value='" + color + "']");
    }

    public static By cardColor = By.xpath("//p[@id='card-color']/canvas");

    //Size
    public static By getSizeByName(String name) {
        return By.xpath("//input[@value='" + name + "']");
    }

    public By cardSizes = By.xpath("//p[@id='card-size']");

    //Gender
    public static By getGenderByName(String gender) {
        return By.xpath("//input[@value='" + gender + "']");
    }

    public static By cardGenders = By.xpath("//p[@id='card-gender']");
}
