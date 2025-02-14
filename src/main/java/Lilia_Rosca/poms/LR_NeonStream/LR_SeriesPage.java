package Lilia_Rosca.poms.LR_NeonStream;

import Lilia_Rosca.components.LR_NeonStream.LR_ContentHeader;
import helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LR_SeriesPage extends BasePage {
// 10.02
    @FindBy(css = "picture[class*=background-picture]")
    public WebElement backImage;

    @FindBy(css = "div [class*=content-header_content-header]")
    public LR_ContentHeader contentHeader;

    public LR_SeriesPage(WebDriver driver) {super(driver);}
}
