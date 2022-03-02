package com.apiTests;

import static com.specification.Specifications.requestSpecification;
import static com.specification.Specifications.responseSpecificationScOk;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetRequestTest {

    @Test
    @DisplayName("Тестирование запроса Get c проверкой status code = 200")
    public void getRequestCheckStatusCode() {
        RestAssured.given()
                   .spec(requestSpecification())
                   .get("/api/users/2")
                   .then()
                   .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Тестирование запроса Get c проверкой key/value по полям id, email, first_name, last_name")
    public void getRequestCheckResponseJsonBody() {
        RestAssured.given()
                   .spec(requestSpecification())
                   .get("/api/users/2")
                   .then()
                   .statusCode(HttpStatus.SC_OK)
                   .assertThat()
                   .body("data.id", Matchers.is(2))
                   .body("data.email", Matchers.is("janet.weaver@reqres.in"))
                   .body("data.first_name", Matchers.is("Janet"))
                   .body("data.last_name", Matchers.is("Weaver"));
    }

    @Test
    @DisplayName("Тестирование запроса Get c валидацией ответа по json схеме")
    public void getRequestCheckResponseWithJsonSchema() {
        RestAssured.given()
                   .spec(requestSpecification())
                   .get("/api/users/2")
                   .then()
                   .spec(responseSpecificationScOk())
                   .assertThat()
                   .body(matchesJsonSchemaInClasspath("SchemaUsersById.json"));
    }
}

