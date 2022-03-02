package com.apiTests;

import static com.specification.Specifications.requestSpecification;

import com.model.CreateUserDTO;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PutRequestTest {

    @Test
    @DisplayName("Тестирование тестового запроса Put c обновлением данных Users по полю job")
    public void putRequestCheckStatusCodeAndJsonBody() {
        String id;

        id = RestAssured.given()
                        .spec(requestSpecification())
                        .body(new CreateUserDTO("morpheus", "leader"))
                        .post("/api/users")
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .assertThat()
                        .body("name", Matchers.is("morpheus"))
                        .body("job", Matchers.is("leader"))
                        .extract()
                        .response()
                        .body()
                        .path("id");

        RestAssured.given()
                   .spec(requestSpecification())
                   .body(new CreateUserDTO("morpheus", "test_it"))
                   .post("/api/users" + id)
                   .then()
                   .statusCode(HttpStatus.SC_CREATED)
                   .assertThat()
                   .body("name", Matchers.is("morpheus"))
                   .body("job", Matchers.is("test_it"));
    }
}

