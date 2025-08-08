package denis_grimaliuc.api.petStore;

import example.api.petstore.PetstoreBaseRequest;

import java.util.HashMap;

public class PetEndpoint extends PetstoreBaseRequest {

    public static HashMap<String, String> addPet(String name, String location) {
        var request = given();

        request.body("""
                {
                    "name": "%s",
                    "location": "%s"
                }
                """.formatted(name, location));

        var response = request.post("/pets");
        handleResponse(response);

        return response.jsonPath().get("$");
    }

    public static HashMap<String, String> getPet(String petId, String location) {
        var request = given();
        request
                .queryParam("location", location)
                .pathParam("pet_id", petId);

        var response = request.get("/pets/{pet_id}");
        handleResponse(response);

        return response.jsonPath().get("$");
    }
}
