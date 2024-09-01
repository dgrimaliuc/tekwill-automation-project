package victor_murashev.junit.neonStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import victor_murashev.ui.neonStream.components.HeroCarousel;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class NeonStreamSmokeTest extends NeonStreamBaseTest {

    HeroCarousel herosCarousel = neonStreamPage.heroCarousel;
    Actions action = new Actions(driver);


    @BeforeEach
    public void placeholdersShouldNotSeen() {
        actions.shouldNotSee(herosCarousel.placeholder);
        actions.shouldSee(herosCarousel);
    }


    @Test
    @DisplayName("Content is displayed")
    public void contentIsDisplayed() throws InterruptedException {

        Thread.sleep(1000);
        actions.shouldSee(herosCarousel.cardContainers.get(0).cardTitle);
        actions.shouldSee(herosCarousel.cardContainers.get(0).backgroundImage);
        actions.shouldSee(herosCarousel.cardContainers.get(0).cardDescription);
        actions.shouldSee(herosCarousel.cardContainers.get(0).watchNowButton);
        actions.shouldSee(herosCarousel.cardContainers.get(0).addToWatchListButton);

    }

    @Test
    @DisplayName("Left Arrow Click Test")
    public void clickOnLeftArrow() throws InterruptedException {
        int indexTab2 = 2;
        Thread.sleep(1000);
        herosCarousel.tabs.get(indexTab2).click();
        String title = herosCarousel.cardContainers.get(indexTab2).cardTitle.getText();
        String description = herosCarousel.cardContainers.get(indexTab2).cardDescription.getText();
        String image = herosCarousel.cardContainers.get(indexTab2).backgroundImage.getAttribute("src");

        //action.moveToElement(herosCarousel.arrowLeft).click().perform();
        herosCarousel.arrowLeft.click();
        //Thread.sleep(2000);
        actions.shouldNotHaveTextToBe(herosCarousel.cardContainers.get(indexTab2 - 1), title);
        actions.shouldNotHaveTextToBe(herosCarousel.cardContainers.get(indexTab2 - 1), description);
        actions.shouldNotHaveAttribute(herosCarousel.cardContainers.get(indexTab2 - 1), "src", image);
    }

    @Test
    @DisplayName("Right Arrow Click Test")
    public void clickOnRightArrow() throws InterruptedException {
        int indexTab1 = 1;

        Thread.sleep(1000);

        herosCarousel.tabs.get(indexTab1).click();
        String title = herosCarousel.cardContainers.get(indexTab1).cardTitle.getText();
        String description = herosCarousel.cardContainers.get(indexTab1).cardDescription.getText();
        String image = herosCarousel.cardContainers.get(indexTab1).backgroundImage.getAttribute("src");

        //action.moveToElement(herosCarousel.arrowRight).click().perform();
        herosCarousel.arrowRight.click();

        actions.shouldNotHaveTextToBe(herosCarousel.cardContainers.get(indexTab1 + 1), title);
        actions.shouldNotHaveTextToBe(herosCarousel.cardContainers.get(indexTab1 + 1), description);
        actions.shouldNotHaveAttribute(herosCarousel.cardContainers.get(indexTab1 + 1), "src", image);
    }

    @Test
    @DisplayName("Watch now Movie test")
    public void watchNowMovieTest() throws InterruptedException {
        Thread.sleep(1000);
        actions.shouldSee(herosCarousel.cardContainers.get(0).watchNowButton);
        var index = herosCarousel.findHeroCard("movie");
        herosCarousel.cardContainers.get(index).watchNowButton.click();
        wait.until(urlContains("/movie/"));
    }

}
