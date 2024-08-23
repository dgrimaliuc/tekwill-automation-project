package IgorTabirta.Junit.PetStore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LocationTest extends BaseLocationTest {

    private final String expectedLocation = "New York";

    @BeforeEach
    public void typeLocation() {


        petStore.location.locationInput.clear(); // очищаем локацию установленную по умолчанию

        petStore.location.locationInput.sendKeys(expectedLocation); //добавляем локацию New York


    }


    @Test
    @DisplayName("Change location Test")
    public void changeLocationTest() {


        petStore.location.changeLocationButton.click(); // кликаем по кнопке Change Location Btn

    }


    @Test
    @DisplayName("Change Location in new Tab")
    public void ChangeLocationInNewTab() {

        petStore.location.changeLocationButton.click();
        petStore.location.openInNewTabBtn.click();


        var tabs = driver.getWindowHandles();
        assertThat(tabs.size(), equalTo(2));
        driver.switchTo().window(tabs.toArray()[1].toString());


    }

    @AfterEach
    public void validateLocation() {

        var url = driver.getCurrentUrl(); // Получем url адрес

        // поверка location в адрессной строке

        assertThat(url, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));

        //проверка location в Pets Section

        var expectedTitleP = "Pets in " + expectedLocation;
        assertThat(petStore.petsSection.title.getText(), equalTo(expectedTitleP));


        // проверка location в Adoptions Section

        var expectedTitleA = "Adoptions in " + expectedLocation;
        assertThat(petStore.adoptionsSection.title.getText(), equalTo(expectedTitleA));
    }

}



