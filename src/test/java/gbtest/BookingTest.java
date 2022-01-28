package gbtest;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class BookingTest extends BaseTest {

    @Test
        // ввод валидных данных
    void bookingPositiveTest() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Альберт\",\n" +
                        "    \"lastname\" : \"Ноббс\",\n" +
                        "    \"totalprice\" : 155,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-01-25\",\n" +
                        "        \"checkout\" : \"2022-02-03\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("booking")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("booking.bookingdates.checkin", CoreMatchers.equalTo("2022-01-25"))
                .body("booking.firstname", CoreMatchers.equalTo("Альберт"));
    }

    @Test
        // пустое значение поля "firstname"
    void bookingNegative1Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : ,\n" +
                        "    \"lastname\" : \"Ноббс\",\n" +
                        "    \"totalprice\" : 155,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-01-25\",\n" +
                        "        \"checkout\" : \"2022-02-03\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("booking")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

    @Test
        // пустое значение поля "lastname"
    void bookingNegative2Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Альберт\",\n" +
                        "    \"lastname\" : ,\n" +
                        "    \"totalprice\" : 155,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-01-25\",\n" +
                        "        \"checkout\" : \"2022-02-03\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("booking")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

    @Test
        // пустое значение поля "totalprice"
    void bookingNegative3Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Альберт\",\n" +
                        "    \"lastname\" : \"Ноббс\",\n" +
                        "    \"totalprice\" : ,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-01-25\",\n" +
                        "        \"checkout\" : \"2022-02-03\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("booking")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

    @Test
        // пустое значение поля "firstname", "lastname"
    void bookingNegative4Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : ,\n" +
                        "    \"lastname\" : ,\n" +
                        "    \"totalprice\" : 155,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-01-25\",\n" +
                        "        \"checkout\" : \"2022-02-03\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("booking")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

    @Test
        // неправильный формат даты поля "checkin"
    void bookingNegative5Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Альберт\" ,\n" +
                        "    \"lastname\" : \"Ноббс\",\n" +
                        "    \"totalprice\" : 155,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-01-35\",\n" +
                        "        \"checkout\" : \"2022-02-03\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("booking")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body(CoreMatchers.equalTo("Invalid date"));
    }

    @Test
        // пустое значение поля "checkin"
    void bookingNegative6Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Альберт\" ,\n" +
                        "    \"lastname\" : \"Ноббс\",\n" +
                        "    \"totalprice\" : 155,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : ,\n" +
                        "        \"checkout\" : \"2022-02-03\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("booking")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

    @Test
        // пустое значение поля "checkout"
    void bookingNegative7Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Альберт\",\n" +
                        "    \"lastname\" : \"Ноббс\",\n" +
                        "    \"totalprice\" : 155,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-04-25\",\n" +
                        "        \"checkout\" : \"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : 0\n" +
                        "}")
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("booking")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

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
                .get("booking")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
