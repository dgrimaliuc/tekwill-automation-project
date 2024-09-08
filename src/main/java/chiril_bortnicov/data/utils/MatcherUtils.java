package chiril_bortnicov.data.utils;

import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.FileInputStream;

public class MatcherUtils {

    public static JsonSchemaValidator matchesJsonSchemaFrom(String path) {
        try {
            return JsonSchemaValidator.matchesJsonSchema(new FileInputStream(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
