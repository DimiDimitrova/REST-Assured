package github;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ComplexMatcher {
    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void complexHamcrestMatchers(){
       RestAssured.get(BASE_URL)
                .then()
                .header("X-RateLimit-Limit", Integer :: parseInt, Matchers.equalTo(60))
                .header("date", date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()))
               .header("X-RateLimit-Limit",
                       response -> Matchers.greaterThan(response.header("X-RateLimit-remaining")));
    }
}
