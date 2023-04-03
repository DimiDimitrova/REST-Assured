package github;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class AssertingBasicResponse {
    @BeforeSuite
    public void setup(){
        RestAssured.baseURI = "https://api.github.com/";
    }
    @Test
    public void convenienceMethod(){
        Response response = RestAssured.get();

        assertEquals(response.statusCode(), 200);
        assertEquals(response.getContentType(),"application/json; charset=utf-8");
    }

    @Test
    public void genericHeader(){
        Response response = RestAssured.get();
        assertEquals(response.getHeader("server"),"GitHub.com");
        assertEquals(response.getHeader("X-RateLimit-Limit"), "60");
    }

    @Test
    public void getHeaders(){
        Response response = RestAssured.get();
        Headers headers = response.getHeaders();
        int size = headers.size();
        List<Header> list = headers.asList();
        boolean isPresent = headers.hasHeaderWithName("header1");
        assertTrue(isPresent);
    }
    @Test
    public void time(){
        Response response = RestAssured.get();
        response.time();
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println( response.getTime());
    }
}
