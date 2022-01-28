package gbtest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {
    static Properties properties;
    static String host;
    static String host1;
    static String host2;
    static String username;
    static String token;

    @BeforeAll
    static void beforeAll() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/application.properties"));
        host = properties.getProperty("host","https://reqres.in/api/");
        host1 = properties.getProperty("host1","https://restful-booker.herokuapp.com/");
        host2 = properties.getProperty("host2","https://api.imgur.com/3/");
        username = properties.getProperty("username");
        token = properties.getProperty("token");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = host1;
    }


}
