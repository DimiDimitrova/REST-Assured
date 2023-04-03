package github;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class HttpMethods {
    public static final String BASE_URL = "https://api.github.com/";
    public static final String TOKEN = "your token";

    @Test
    public void headTest() {
        RestAssured.head(BASE_URL)
                .then()
                .statusCode(200)
                .body(Matchers.emptyOrNullString());
    }

    @Test
    public void optionTest() {
        RestAssured.options(BASE_URL)
                .then()
                .statusCode(204)
                .header("access-control-allow-methods", Matchers.equalTo("GET, POST, PATCH, PUT, DELETE"))
                .body(Matchers.emptyOrNullString());
    }

    @Test(description = "Create a repo")
    public void postRepo() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\":\"Hello Rest Assured Demo\",\"description\":\"This is my first repo with rest assured!\"," +
                            "\"private\":true,\"is_template\":true}")
                .when()
                    .post(BASE_URL + "user/repos")

                .then()
                    .statusCode(201);
    }

    @Test(description = "Update a repo")
    public void patchRepo() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\":\"Hello Rest Assured patch\"}")
                .when()
                    .patch(BASE_URL + "repos/DimiDimitrova/Hello-Rest-Assured-Demo")
                .then()
                    .statusCode(200);
    }

    @Test(description = "Delete a repo")
    public void deleteRepo() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                .when()
                    .patch(BASE_URL + "repos/DimiDimitrova/Hello-Rest-Assured-patch")
                .then()
                    .statusCode(204);
    }
}