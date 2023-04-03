package github;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

public class MapsTests {
    public static final String BASE_URL = "https://api.github.com";

    Map<String, String> expectedHeaders = Map.of("Content-Encoding","gzip",
            "Access-Control-Allow-Origin","*");

    @Test
    public void mapToTestHeaders(){
        RestAssured.get(BASE_URL)
                .then()
                .headers(expectedHeaders);
    }
}
