package Magda_Petrachi.API;

import example.api.petstore.PetstoreBaseRequest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static Magda_Petrachi.PetStoreAPI.PetEndPoint.addPet;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PetStoreAPI extends PetstoreBaseRequest {

    String location = "New York";

    @Test
    public void getAdoptionTest() {
        String id = "0698bf0a-e81a-4267-945a-bd4d54c4508a";
        var request = given();

        request
                .queryParam("location", location)
                .pathParam("id", id);


        var response = request.get("/adoptions/{id}");
        handleResponse(response);


        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("id", equalTo(id))
                .body("location", equalTo(location))
                .body(matchesJsonSchemaFrom(addAdoptionSchema.json))
    }

    @Test
    public void getSinglePetTest() {

        String petId = "0698bf0a-e81a-4267-945a-bd4d54c4508a";

        var request = given();

        request
                .queryParam("location", location)
                .pathParam("pet_id", petId);

        var response = request.get("/pets/{pet_id}");

        handleResponse(response);

        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("id", equalTo(petId))
                .body("location", equalTo(location))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"));
    }

    @Test
    public void addPetTest() {
        var request = given();

        request.body("""
                {
                    "name": "Dexter",
                    "location": "Chisinau"
                }         
                """);

        var response = request.post("/pets");
        handleResponse(response);

        response.then().assertThat()
                .statusCode(201)
                .time(lessThan(2000L))
                .body("name", equalTo("Dexter"))
                .body("location", equalTo("Chisinau"))
                .body("status", equalTo("available"))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"));
    }

    @Test
    public void patchPetTest() {

        String petId = "d61787ae-1ad9-4bea-804e-e91df658ab3c";

        var request = given();

        request.pathParam("id", petId)
                .body("""
                        {
                            "name": "Dexter 2",
                            "location": "Chisinau",
                            "status": "adopted"
                        }         
                        """);

        var response = request.patch("/pets/{id}");

        handleResponse(response);

        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("name", equalTo("Dexter 2"))
                .body("status", equalTo("adopted"))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"));
    }

    @Test
    public void deleteSinglePetTest() {
        String id = "d61787ae-1ad9-4bea-804e-e91df658ab3c";
        var request = given();

        request
                .queryParam("location", "Chisinau")
                .pathParam("pet_id", id);

        var response = request.delete("/pets/{pet_id}");

        handleResponse(response);

        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("message", equalTo("Pet removed: " + id));
    }

    @Test
    public void deleteMultiplePetTest() {
        String location = "Chisinau2";

        var req = given();

        req.queryParam("location", location);

        var resp = req.delete("/pets");

        handleResponse(resp);

        resp.then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("message", equalTo("Removed all pets from " + location));
    }

    @Test
    public void patchPetTest2() {

        HashMap<String, String> pet = addPet("Dexter", "Ratus");
        String petId = pet.get("id");

        var request = given();

        request.pathParam("id", petId)
                .body("""
                        {
                            "name": "Dexter 2",
                            "location": "Chisinau",
                            "status": "adopted"
                        }         
                        """);

        var response = request.patch("/pets/{id}");

        handleResponse(response);

        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("name", equalTo("Dexter 2"))
                .body("status", equalTo("adopted"))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"));
    }

}
