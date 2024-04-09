package org.acme;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HelloServiceTest {

    private static final String EXPECTED_RESPONSE = "[{\"lib1 returned:\":\"hello hello world from lib1 hello world from lib1 decision table\"},{\"lib2 returned:\":\"hello hello world from lib2 hello world fom from lib2 decision table\"}]";

    @Test
    public void testHelloServiceEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is(EXPECTED_RESPONSE));
    }    

}

