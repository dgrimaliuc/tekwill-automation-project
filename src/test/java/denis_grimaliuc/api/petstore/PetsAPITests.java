package denis_grimaliuc.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static denis_grimaliuc.api.petstore.endpoints.AdoptionsEndpoint.*;
import static denis_grimaliuc.api.petstore.endpoints.PetsEndpoint.*;
import static denis_grimaliuc.data.enums.Status.*;
import static denis_grimaliuc.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class PetsAPITests {
    @Test
    @DisplayName("Get all pets test")
    public void getPetsTest() {
        var response = getPets("Chisinau", AVAILABLE);
        response.then().assertThat().statusCode(200).body("size()", greaterThan(0)).body("location", everyItem(equalTo("Chisinau"))).body("status", everyItem(equalTo(AVAILABLE.toString()))).time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get all pets schema test")
    public void getPetsSchemaTest() {
        var response = getPets("Chisinau");

        response.then().assertThat().body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"));


    }

    @Test
    @DisplayName("Get all pets without status test")
    public void getPetsWithoutStatusTest() {
        var response = getPets("Chisinau");
        response.then().assertThat().statusCode(200)
                .body("size()", greaterThan(0)).body("location", everyItem(equalTo("Chisinau")))
                .body("status", everyItem(either(equalTo(ADOPTED.toString())).or(equalTo(ONHOLD.toString()))
                        .or(equalTo(AVAILABLE.toString())))).time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get all pets without location test")
    public void getPetsWithoutLocationTest() {
        var response = getPets();
        response.then().assertThat().statusCode(400)
                .body("error", equalTo("Missing location"));
    }

    @Test
    @DisplayName("Add pet test")
    public void addPetTest() {
        String name = "Rex";
        String location = "Chisinau";

        var response = addPet(location, name);

        response.then().assertThat().statusCode(201)
                .body("name", equalTo(name)).body("location", equalTo(location)).body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json")).time(lessThan(1000L));
    }

    @Test
    @DisplayName("Add pet without location test")
    public void addPetWithoutLocationTest() {
        var response = addPet("", "Rex");
        response.then().assertThat().statusCode(400)
                .body("error", equalTo("Props are required: location")).time(lessThan(1000L));
    }

    @Test
    @DisplayName("Deleting all pets deletes all adoptions test")
    public void deletePetsTest() {
        String location = "Plett";
        var response = deletePets(location);

        response.then().assertThat().statusCode(200)
                .body("message", equalTo("Removed"))
                .time(lessThan(1500L));

        getPets(location).then().assertThat().body("size()", equalTo(0));
        getAdoptions(location, "").then().assertThat().body("size()", equalTo(0));
    }

    @Test
    @DisplayName("Get pets with !status test")
    public void getCustomStatusPets() {
        getPets("Chisinau", "!" + AVAILABLE + "&!" + ADOPTED).
                then().assertThat().statusCode(200).body("status", everyItem(equalTo(ONHOLD.toString())));
    }


    @Test
    @DisplayName("Update pet test")
    public void updatePetTest() {
        var location = "Chisinau";
        var newName = "Rex";
        var pet = addPet(location, "Fluffy").jsonPath().getString("id");

        var response = updatePet(pet, location, ADOPTED.toString(), newName);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(newName))
                .body("location", equalTo(location))
                .body("status", equalTo(ADOPTED.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .time(lessThan(1000L));

    }


    @Test
    @DisplayName("Get pet by id test")
    public void getPetByIdTest() {
        var location = "Plett";
        var name = "Rex";

        String id = addPet(location, name).jsonPath().getString("id");

        getPet(id, location)
                .then()
                .assertThat()
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("location", equalTo(location))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get pet by invalid format id test")
    public void getPetByInvalidFormatIdTest() {
        String id = "123";
        getPet(id, "Chisinau")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Pet ID is invalid"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get pet by invalid id test")
    public void getPetByInvalidIdTest() {
        String id = "5660cd92-9d76-403b-bd28-e07e83a2e545";
        String location = "Chisinau";

        getPet(id, location)
                .then()
                .assertThat()
                .statusCode(404)
                .body("error", equalTo("Pet %s not found in %s".formatted(id, location)))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Deleting an adopted pet is impossible and will return 400 bad request")
    public void deletingAdoptedPetIsImpossibleTest() {
        var location = "Chisinau";
        var petId = addPet(location, "Fluffy")
                .jsonPath()
                .getString("id");

        createAdoption(location, List.of(petId));

        deletePet(petId, location)
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Pet '%s' is onhold".formatted(petId)));
    }

    @Test
    @DisplayName("When Adoption with pet is approved or denied Pet can be removed ")
    public void whenAdoptionWithPetIsApprovedItCanBeDeleted() {
        var location = "Chisinau";
        var petId = addPet(location, "Fluffy")
                .jsonPath()
                .getString("id");

        var adoptionId = createAdoption(location, List.of(petId))
                .jsonPath()
                .get("id").toString();

        updateAdoption(adoptionId, "approved", location);

        deletePet(petId, location)
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Pet removed: %s".formatted(petId)));
    }


}
