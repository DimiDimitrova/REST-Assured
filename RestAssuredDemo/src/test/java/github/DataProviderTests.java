package github;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTests {
    public static final String BASE_URL = "https://api.github.com/";

    @DataProvider
    public Object[][] names(){
        return new Object[][]{
                {"DimiDimitrova"},
                {"Ivaylo996"}
        };
    }

    @Test(dataProvider = "names")
    public void complexBodyExampleWithDataProvider(String name){
        RestAssured.get(BASE_URL + "users/" + name)
                .then()
                .body("url",response -> Matchers.containsString(response.body().jsonPath().get("login")));
    }
}