package com.apiTests.testdocker;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class testSI {

    @Test
    @Tags(value = {@Tag("testUI")})
    public void logOnSpec() {
        given().multiPart("username", "testUser")
               .multiPart("password", "P@55word123SpmSecured")
               .post("http://stspma-apc001lk.corp.dev.vtb:8087/login")
               .then()
               .statusCode(200);
    }
}
