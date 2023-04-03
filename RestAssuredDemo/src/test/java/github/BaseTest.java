package github;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class BaseTest {
    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void getGitHubContent() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void peek() {
        RestAssured.get(BASE_URL)
                .peek();
    }

    @Test
    public void prettyPeek() {
        RestAssured.get(BASE_URL)
                .prettyPeek();
    }

    // print only the body
    @Test
    public void print() {
        RestAssured.get(BASE_URL)
                .print();
    }

    // print only the body
    @Test
    public void prettyPrint() {
        RestAssured.get(BASE_URL)
                .prettyPrint();
    }
}