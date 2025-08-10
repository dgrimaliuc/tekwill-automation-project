package Magda_Petrachi.API;

import example.api.petstore.PetstoreBaseRequest;
import org.junit.jupiter.api.Test;

import static Magda_Petrachi.PetStoreAPI.AdoptionEndPoint.addAdoption;
import static Magda_Petrachi.PetStoreAPI.PetEndPoint.addPet;
import static Magda_Petrachi.PetStoreAPI.PetEndPoint.getPet;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class AdoptionAPI extends PetstoreBaseRequest {

    String location = "Chisinau";

    @Test
    public void adoptionPetTest() {
        var request = given();

        request.body("""
                {
                    "name": "Dexter",
                    "location": "Chisinau"
                }         
                """);

        var response = request.post("/adoption");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(201)
                .time(lessThan(2000L))
                .body("name", equalTo("Dexter"))
                .body("location", equalTo("Chisinau"))
                .body("status", equalTo("available"))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"));
    }

    @Test
    public void getMultipeAdoptionTest() {
        String status = "available";

        var request = given();

        request
                .queryParam("location", location)
                .queryParam("status", status);

        var response = request.post("/adoption");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(201)
                .time(lessThan(2000L))
                .body("location", equalTo("Chisinau"))
                .body("status", equalTo("available"))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"));
    }

    @Test
    public void creatAdoptionTest() {
        var pet = addPet("buddy", location);
        var request = given();
        request.body("""
                {
                    "name": "Dexter",
                    "location": "Chisinau"
                }         
                """);
        var response = request.post("/adoption");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(201)
                .time(lessThan(2000L))
                .body("location", equalTo(location))
                .body("pets[0]", equalTo(pet.get("id")))
                .body("status", equalTo("available"))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"));

        pet = getPet(pet.get("status"), location);
        assertThat(pet.get("status"), equalTo("onhold"));
    }

    @Test
    public void makeAdoptionDeniedTest() {
        var pet = addPet("buddy", location);
        var adoption = addAdoption(location, pet.get("id"));

        var request = given();
        request.body("""
                        {
                            "status": "denied",
                            "location": "%s"
                        }         
                        """.formatted(location))
                .pathParam("id", adoption.get("id"));
        var response = request.patch("/adoption/{id}");

        handleResponse(response);

        response.then().assertThat()
                .statusCode(201)
                .time(lessThan(2000L))
                .body("location", equalTo(location))
                .body("status", equalTo("denied"))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"));
    }

    @Test
    public void deleteSingleAdoptionTest() {
        var pet = addPet("Buddy", location);
        var adoption = addAdoption(location, pet.get("id"));

        String adoptId = adoption.get("id");


        var request = given();
        request
                .queryParam("location", location)
                .queryParam("id", adoptId);

        var response = request.delete("/adoption/{id}");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(201)
                .time(lessThan(2000L))
                .body("message", equalTo("Adoption removed" + adoptId))
                .body("status", equalTo("denied"))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"));

        pet = getPet(pet.get("id"), location);

    }
}
