package denis_grimaliuc.api.petStore;

import example.api.petstore.PetstoreBaseRequest;

import java.util.ArrayList;
import java.util.HashMap;

import static example.actions.BaseActions.gson;

public class AdoptionsEndpoint extends PetstoreBaseRequest {

    public static HashMap<String, String> addAdoption(String location, String... pets) {
        var request = given();

        request.body("""
                {
                    "location": "%s",
                    "pets": %s
                }
                """.formatted(location,
                gson.toJson(pets)
        ));

        var response = request.post("/adoptions");
        handleResponse(response);

        return response.jsonPath().get("$");
    }

    public static ArrayList<HashMap<String, String>> getAdoptions(String location) {
        var request = given();
        request
                .queryParam("location", location);

        var response = request.get("/adoptions");
        handleResponse(response);
        return response.jsonPath().get("$");
    }
}
