package github;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class BasicValidationsTests {
    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void basicValidationResponse(){
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                    .statusCode(200)
                .and().assertThat()
                    .contentType(ContentType.JSON)
                    .header("X-RateLimit-Limit","60");
    }

    @Test
    public void hamcrestExample(){
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(equalTo(200))
                .statusCode(lessThan(300))
                .statusCode(anyOf(equalTo(200),equalTo(202)))     // !!!!
                .header("etag",notNullValue());
                //.header("etag",is(emptyString()));
    }
}
