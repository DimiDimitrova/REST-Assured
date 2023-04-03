package github;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.example.User;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MapperTests {
//    public static void main(String[] args) {
//        ObjectMapper mapper = new ObjectMapper();
//    }
    public static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void objectMapping() {
        User user = RestAssured.get(BASE_URL)
                .as(User.class);

        Assert.assertEquals(user.getId(),19369327);
        Assert.assertEquals(user.getPublicRepos(), 2);
    }

    @Test
    public void objectMappingRelyingOnMapperType(){
        User user = RestAssured.get(BASE_URL)
                .as(User.class, ObjectMapperType.JACKSON_2);

        Assert.assertEquals(user.getLogin(),"rest-assured");
    }
}