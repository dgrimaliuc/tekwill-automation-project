package Magda_Petrachi.PetStoreAPI;

import example.api.petstore.PetstoreBaseRequest;

import java.util.HashMap;

import static groovy.json.JsonOutput.toJson;

public class AdoptionEndPoint extends PetstoreBaseRequest {
    private static final String adoptionEndpoint = "/adoptions";

    public static HashMap<String, String> addAdoption(String location, String... pets) {
        var request = given();
        request.body("""
                {
                    "pets": "%s",
                    "location": "%s"
                }         
                """.formatted(location,
                toJson(pets)));

        var response = request.post("/adoption");
        handleResponse(response);

        return response.jsonPath().get("$");
    }

}