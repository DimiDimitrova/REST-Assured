package github;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class ResponseBodyTests {
    public static final String BASE_URL = "https://api.github.com/rate_limit";
    //   Response body methods:
    //  - peek(), print()
    //  - asByteArray(), asInputStream()
    //  - asString()
    //  - as()
    //  - jsonPath(), xmlPath()
    @Test
    public void jsonPathReturnsMap(){
        Response response = RestAssured.get(BASE_URL);
        // ResponseBody<?> body = response.body();
       // JsonPath jPath = body.jsonPath();
        // or
        JsonPath jPath = response.body().jsonPath();
        Map<String, String> fullJson = jPath.get();
        Map<String, String> subMap = jPath.get("resources");
        Map<String, String> subSubMap = jPath.get("resources.core");

        int value = jPath.get("resources.core.limit");

        System.out.println(fullJson);
        System.out.println(subMap);
        System.out.println(subSubMap);
        System.out.println(value);
        Assert.assertEquals(value,60);
    }

    @Test
    public void complexBodyTest(){
        RestAssured.get("https://api.github.com/" + "users/DimiDimitrova")
                .then()
                .body("url", response -> Matchers.containsString("DimiDimitrova"))
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")));
    }

    @Test
    public void nestedBodyValidation(){
        RestAssured.get(BASE_URL)
                .then()
                .rootPath("resources.core")
                    .body("limit", Matchers.equalTo(60))
                    .body("remaining", Matchers.lessThanOrEqualTo(60))
                    .body("reset",Matchers.notNullValue())
                .rootPath("resources.search")
                    .body("limit", Matchers.equalTo(10))
                    .body("remaining", Matchers.lessThanOrEqualTo(10));
    }
}