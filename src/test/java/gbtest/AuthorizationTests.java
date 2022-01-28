package gbtest;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class AuthorizationTests extends BaseTest {

    @Test
        // авторизация с валидными значениями
    void AccountInfoTest() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("auth")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
        // пустое поле ввода username
    void AuthorizationNegative1Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"username\" : ,\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("auth")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

    @Test
        // пустое поле ввода password
    void AuthorizationNegative2Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\" ,\n" +
                        "    \"password\" : \n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("auth")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

    @Test
        // пустое поле ввода password и username
    void AuthorizationNegative3Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"username\" : ,\n" +
                        "    \"password\" : \n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("auth")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body(CoreMatchers.equalTo("Bad Request"));
    }

    @Test
        // Неверное значение поля username
    void AuthorizationNegative4Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"username\" : \"admin123\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("auth")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("reason", CoreMatchers.equalTo("Bad credentials"));
    }

    @Test
        // Неверное значение поля password
    void AuthorizationNegative5Test() {
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"word123\"\n" +
                        "}")
                .log()
                .uri()
                .when()
                .post("auth")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("reason", CoreMatchers.equalTo("Bad credentials"));
    }
}
