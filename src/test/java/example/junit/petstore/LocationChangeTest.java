package example.junit.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationChangeTest extends BasePetstoreTest {


    @Test
    @DisplayName("Change location in new tab test")
    public void changeLocationTestQuery() {
        var expectedLocation = "SanFrancisco";
        driver.get("https://petstore-eb41f.web.app/?location=" + expectedLocation);

        var url = driver.getCurrentUrl();

        assertThat(url, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));
        var expectedTitleP = "Pets in " + expectedLocation;
        assertThat(petStore.petsSection.title.getText(), (equalTo(expectedTitleP)));

        var expectedTitleA = "Adoptions in " + expectedLocation;
        assertThat(petStore.adoptionsSection.title.getText(), equalTo(expectedTitleA));
    }


}
