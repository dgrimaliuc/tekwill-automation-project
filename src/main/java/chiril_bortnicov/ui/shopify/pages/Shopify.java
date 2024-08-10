package chiril_bortnicov.ui.shopify.pages;

import org.openqa.selenium.By;

public class Shopify {

    public By cardContainer = By.id("card-container");

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

}
