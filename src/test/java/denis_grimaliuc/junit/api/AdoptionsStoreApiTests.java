package denis_grimaliuc.junit.api;

import example.api.petstore.PetstoreBaseRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static denis_grimaliuc.api.petStore.AdoptionsEndpoint.addAdoption;
import static denis_grimaliuc.api.petStore.AdoptionsEndpoint.getAdoptions;
import static denis_grimaliuc.api.petStore.PetEndpoint.addPet;
import static denis_grimaliuc.api.petStore.PetEndpoint.getPet;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;

public class AdoptionsStoreApiTests extends PetstoreBaseRequest {

    String location = "New York";

    @Test
    public void getAdoptionTest() {
        String id = "d13b6192-5a80-48b3-aa7a-f23e0a65d2e3";

        var request = given();
        request
                .queryParam("location", location)
                .pathParam("id", id);

        var response = request.get("/adoptions/{id}");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("id", equalTo(id))
                .body("location", equalTo(location))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"));
    }

    @Test
    public void getAdoptionWithWrongIdTest() {
        String id = "NA";

        var request = given();
        request
                .queryParam("location", location)
                .pathParam("id", id);

        var response = request.get("/adoptions/{id}");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error", equalTo("Adoption ID is invalid"));
    }

    @Test
    public void getAdoptionWithMissingLocationTest() {
        String id = "d13b6192-5a80-48b3-aa7a-f23e0a65d2e3";

        var request = given();
        request
                .pathParam("id", id);

        var response = request.get("/adoptions/{id}");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error", equalTo("Missing location"));
    }

    @Test
    public void getMultipleAdoptionsTest() {
        String status = "available";
        var request = given();
        request
                .queryParam("location", location)
                .queryParam("status", status);

        var response = request.get("/adoptions");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"));
    }

    @Test
    public void createAdoptionTest() {
        var pet = addPet("Buddy", location);

        var request = given();
        request
                .body("""
                        {
                            "location": "%s",
                            "pets": ["%s"]
                        }
                        """.formatted(location, pet.get("id")));

        var response = request.post("/adoptions");

        handleResponse(response);

        response
                .then().assertThat()
                .statusCode(201)
                .body("location", equalTo(location))
                .body("pets[0]", equalTo(pet.get("id")))
                .body("status", equalTo("available"))
                .time(lessThan(2000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"));

        pet = getPet(pet.get("id"), location);
        assertThat(pet.get("status"), equalTo("onhold"));
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "denied, available",
                    "approved, adopted",
            }
    )
    public void patchAdoptionTest(String newAdoptionStatus, String newPetStatus) {

        var pet = addPet("Buddy", location);
        var adoption = addAdoption(location, pet.get("id"));

        var request = given();

        request
                .body("""
                        {
                        "location": "%s",
                        "status": "%s"
                        } 
                         """.formatted(location, newAdoptionStatus))
                .pathParam("id", adoption.get("id"));

        var response = request.patch("/adoptions/{id}");

        handleResponse(response);

        response.then().assertThat()
                .time(lessThan(2000L))
                .statusCode(200)
                .body("location", equalTo(location))
                .body("status", equalTo(newAdoptionStatus))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"));

        pet = getPet(pet.get("id"), location);
        assertThat(pet.get("status"), equalTo(newPetStatus));
    }

    @Test
    public void deleteSingleAdoptionTest() {

        var pet = addPet("Buddy", location);
        var adoption = addAdoption(location, pet.get("id"));

        String adoptId = adoption.get("id");

        var request = given();
        request.pathParam("id", adoptId)
                .queryParam("location", location);

        var response = request.delete("/adoptions/{id}");

        handleResponse(response);

        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("message", equalTo("Adoption removed: " + adoptId));

        pet = getPet(pet.get("id"), location);
        assertThat(pet.get("status"), equalTo("available"));
    }

    @Test
    public void deleteMultipleAdoptionTest() {

        var request = given();
        request.queryParam("location", location);
        var response = request.delete("/adoptions");
        handleResponse(response);
        var remainedAdoptions = getAdoptions(location);
        assertThat(remainedAdoptions.size(), equalTo(0));
    }
}



