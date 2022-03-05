package com.apiTests;

import static com.specification.Specifications.requestSpecification;

import com.model.UserDTO;
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
                        .spec(requestSpecification())//---> Указание RequestSpecification для формирования request
                        .body(new UserDTO("morpheus", "leader"))//---> body для запроса с методом POST
                        .post("/api/users")//---> Endpoint для выполнения запроса GET
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)//---> Проверка статус код
                        .assertThat()
                        .body("name", Matchers.is("morpheus"))//---> Проверка Body по key и value в json
                        .body("job", Matchers.is("leader"))//---> Проверка Body по key и value в json
                        .extract()
                        .response()
                        .body()
                        .path("id");//---> указания поля из Response Json для извлечения данных

        RestAssured.given()
                   .spec(requestSpecification())//---> Указание RequestSpecification для формирования request
                   .body(new UserDTO("morpheus", "test_it"))//---> body с обновленными данными для запроса с методом PUT
                   .post("/api/users" + id)//---> Endpoint для выполнения запроса GET
                   .then()
                   .statusCode(HttpStatus.SC_CREATED)//---> Проверка статус код
                   .assertThat()
                   .body("name", Matchers.is("morpheus"))//---> Проверка Body по key и value в json
                   .body("job", Matchers.is("test_it"));//---> Проверка Body по key и value в json
    }
}

