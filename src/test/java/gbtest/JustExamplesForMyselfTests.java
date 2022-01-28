package gbtest;


import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JustExamplesForMyselfTests extends BaseTest{
    @BeforeAll
    static void beforeBooking() {
        RestAssured.baseURI = host2;
    }

    @Test
    void getAccountPositiveTest() {
        given()
                .header("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get( "account/{username}", username)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.url", CoreMatchers.equalTo(username));
    }

    @Test
    void getAccountSettingsTest() {
        given()
                .header("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .body("success", CoreMatchers.is(true))
                .body("data.url", CoreMatchers.equalTo(username))
                .when()
                .get( "account/" + username)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    //https://restful-booker.herokuapp.com/booking
    @Test
    void getBookingTest() {

        given()
                .header("Content-Type", "application/json")
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .when()
                .get( host1 + "booking")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getReqresTest() {
        given()
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .when()
                .get( host + "register")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
