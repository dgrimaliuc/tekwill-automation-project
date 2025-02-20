package IonErm.junit.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static IonErm.api.petstore.PetEndpoint.*;
import static IonErm.utility.DogNameGenerator.generateDogName;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class ApiPetstoreTest {

    public static String name = generateDogName();
    public static String location = "Chisinau";
    public static String status = "available";

    /* GET */
    @Test
    @DisplayName("Get pet by id")
    public void getPetById() {
        String pet_id = createPet(name, location).jsonPath().get("id");
        getPet(pet_id, location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"));
    }

    @Test
    @DisplayName("Get multiple pets test")
    public void getMultiplePetsTest() {
        String status = "adopted";
        getPets(status, location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"))
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)));
    }

    @Test
    @DisplayName("Get pet by empty id test")
    public void getPetByEmptyIdNegativeTest() {
        getPet("", "")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test
    @DisplayName("Get pet by wrong id test")
    public void getPetByWrongIdNegativeTest() {
        getPet("1ab195fc-ea13-4258-be91-b63c81b7883t", "Chisinau")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test
    @DisplayName("Get pet by unexciting id test")
    public void getPetByUnexcitingIdTest() {
        getPet("53c6fa1b-876a-4690-b76f-abd3f022de6b", location)
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(5000L))
                .body("error", equalTo("Pet 53c6fa1b-876a-4690-b76f-abd3f022de6b not found in Chisinau"));
    }

    @Test
    @DisplayName("Get all pets test")
    public void getAllPetsTest() {
        getPets(status, location)
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"))
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)));
    }

    @Test
    @DisplayName("Get all pets empty location test")
    public void getAllPetsEmptyLocationTest() {
        getPets(status, "")
                .then()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error", equalTo("Missing location"));
    }

    /* POST */
    @Test
    @DisplayName("Add pet positive test")
    public void addPetTest() {
        createPet(name, location)
                .then()
                .assertThat()
                .statusCode(201)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(name))
                .body("location", equalTo(location));
    }

    @Test
    @DisplayName("Add pet negative test")
    public void addPetNegativeTest() {
        createPet("", "")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Props are required: name,location"));
    }

    @Test
    @DisplayName("Empty body test")
    public void emptyBodyTest() {
        createPet(null, null, true)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body(equalTo("Unexpected end of JSON input"));
    }

    /* PATCH */
    @Test
    @DisplayName("Patch a single pet test")
    public void patchAPetTest() {
        String newName = generateDogName();
        var pet_id = createPet(name, location).jsonPath().get("id").toString();
        patchPet(pet_id, String.format(
                """
                            {
                               "location":"%s",
                               "status":"%s",
                               "name":"%s"
                            }
                        """, location, status, newName))
                .then()
                .assertThat()
                .time(lessThan(5000L))
                .statusCode(200)
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("location", equalTo(location))
                .body("name", equalTo(newName))
                .body("status", equalTo(status));
    }

    @Test
    @DisplayName("Patch single pet test")
    public void patchSinglePetTestTest() {
        String newName = generateDogName();
        var pet_id = createPet(name, location).jsonPath().get("id").toString();
        patchPet(pet_id, String.format(
                """
                        {
                            "name":"%s",
                            "location":"%s",
                            "status":"%s"
                        }
                        """, newName, location, status
        ))
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("location", equalTo(location))
                .body("status", equalTo(status));
    }

    @Test
    @DisplayName("Patch pet empty id and location test")
    public void patchEmptyIdAndLocationPetTestTest() {
        patchPet(null, ("""
                {}
                """))
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(4000L))
                .body("error", equalTo("Props are required: id,location"));
    }

    @Test
    @DisplayName("Patch pet invalid id test")
    public void patchInvalidIdPetTestTest() {
        String pet_id = "61d36976-401b-46e6-846b-10ec5f6a0f76"; //invalid
        patchPet(pet_id, String.format(
                """
                        {
                            "name":"%s",
                            "location":"%s",
                            "status":"%s"
                        }
                        """, name, location, status
        ))
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(4000L))
                .body(equalTo("Pet not found"));
    }

    /* DELETE */
    @Test
    @DisplayName("Delete single pet test")
    public void deleteSinglePetTest() {
        String pet_id = "54a144a4-1bbf-453a-8b67-d2182b36371c";
        deletePet(pet_id, location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Pet removed: " + pet_id));
    }

    @Test
    @DisplayName("Delete one pet test")
    public void deleteOnePetTest() {
        String pet_id = createPet(name, "Chisinau").jsonPath().get("id");
        deletePet(pet_id, location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Pet removed: " + pet_id));
    }

    @Test
    @DisplayName("Delete one pet with invalid id test")
    public void deleteOnePetWithInvalidIdTest() {
        String pet_id = "###";
        deletePet(pet_id, location)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test
    @DisplayName("Delete all pets test")
    public void deleteAllPetsTest() {
        deletePets(location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Removed all pets from " + location));
    }
}
