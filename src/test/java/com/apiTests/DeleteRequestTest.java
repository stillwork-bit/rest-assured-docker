package com.apiTests;

import static com.specification.Specifications.requestSpecification;

import com.model.CreateUserDTO;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteRequestTest {

    @Test
    @DisplayName("Тестирование запроса Delete")
    public void deleteRequestCheckStatusCode() {
        int id;

        id = Integer.parseInt(RestAssured.given()
                                         .spec(requestSpecification())
                                         .body(new CreateUserDTO("morpheus", "leader"))
                                         .post("/api/users")
                                         .then()
                                         .statusCode(HttpStatus.SC_CREATED)
                                         .extract()
                                         .response()
                                         .body()
                                         .path("id"));

        RestAssured.given()
                   .spec(requestSpecification())
                   .delete("/api/users/" + id)
                   .then()
                   .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}

