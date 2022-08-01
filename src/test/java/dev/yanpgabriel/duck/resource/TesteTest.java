package dev.yanpgabriel.duck.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
class TesteTest {

    @Test
    void teste() {
        RestAssured.given()
                .when()
                    .get("/api/teste")
                .then()
                    .statusCode(200)
                    .body(CoreMatchers.is("Olá Mundo"));
    }
}
