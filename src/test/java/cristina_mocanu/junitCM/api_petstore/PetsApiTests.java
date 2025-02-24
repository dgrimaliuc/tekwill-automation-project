package cristina_mocanu.junitCM.api_petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cristinamocanu.api.petStore.PetsEndPoint.*;
import static example.api.petstore.PetstoreBaseRequest.*;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static helpers.Helpers.log;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetsApiTests {

    @Test
    @DisplayName("Get pet by id")
    public void testGetPetById() {

     getPet("970530b0-8ae5-44a1-94ac-ea72d6f9d76e", "Chisinau" ).then()
             .assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"));


    }

    @Test
    @DisplayName("Get adoptions by id")
    public void testGetAdoptionsById() {


        var response = given()
                .queryParam("location", "Chisinau")
                .pathParam("id",
                        "484429c0-afad-4b41-a6eb-2292e4350dcf")
                .get("/adoptions/{id}");


        handleResponse(response);


        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("id", equalTo("484429c0-afad-4b41-a6eb-2292e4350dcf"));
    }


    @Test
    @DisplayName("Get multiple pets test")
    public void testGetPets() {
String location = "Chisinau";
String status = "adopted";

        var response = getPets (location, status);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"))
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)));


    }


    @Test
    @DisplayName("Get pet by unexciting id test")
    public void getPetByWrongId() {
        String petId = "53c6fa1b-876a-4690-b76f-abd3f022de6b";
        getPet(petId,"Chisinau")
        .then().assertThat()
                .statusCode(404)
                .time(lessThan(2000L))
                .body("error", equalTo(String.format("Pet %s not found in Chisinau",petId)));

    }

    @Test
    @DisplayName("Create pet negative test")
    public void createPetEmptyBodyTest() {


   createPet(null, null, true).then().assertThat()
                .time(lessThan(1000L))
                .statusCode(400)
                .body(equalTo("Unexpected end of JSON input"));


}
    @Test
    @DisplayName("Patch pet test")
    public void patchPetTest() {


        String location = "Chisinau";
        String name = "John";
        String newName = "John Doe";
        String newStatus = "adopted";
        var petId = createPet(name, location).jsonPath().get("id").toString();
        log.info("Created Pet id: " + petId);

      patchPet(petId, String.format("""
                        
                        { "location": "%s",
                        "status": "%s",
                        "name": "%s"}
                        
                        """, location, newStatus, newName)).then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("status", equalTo(newStatus));

    }

    @Test
    @DisplayName("Delete multiple pets Test")
    public void deleteMultiplePetsTest(){

        var response = given()
                .queryParam("Location", "Chisinau")
                .delete("/pets");

  handleResponse(response);

      response.then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
              .body("message", equalTo("Removed all pets from Chisinau"));

    }
}