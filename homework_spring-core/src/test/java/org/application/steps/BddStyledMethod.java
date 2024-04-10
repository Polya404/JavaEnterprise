package org.application.steps;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BddStyledMethod {

    public static void GETTask(String taskNumber) {
        given().contentType(ContentType.JSON)
                .when().get(String.format("http://localhost:8080/api/v1/task/get/%s", taskNumber))
                .then().body("status", is("DONE"));
    }

    public static void performContainsCollection() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/v1/task/get")
                .then()
                .body("status", containsInAnyOrder("TO_DO", "TO_DO", "TO_DO", "TO_DO", "TO_DO",
                        "DONE", "TO_DO", "TO_DO", "TO_DO", "TO_DO",
                        "TO_DO", "TO_DO", "TO_DO", "TO_DO", "TO_DO"))
                .statusCode(200);
    }

    public static void performPathParameter() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParams("id", 3)
                .when()
                .get("http://localhost:8080/api/v1/task/get/{id}")
                .then()
                .body("status", containsString("DONE"))
                .statusCode(200);
    }

    public static void performPOSTWithBodyParameter(String url) {
        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("id", "5");
        postContent.put("fullName", "John Mitchel");

        given()
                .contentType(ContentType.JSON)
                .with()
                .body(postContent)
                .when()
                .post(url)
                .then()
                .body("fullName", Is.is("John Mitchel"));
    }
}
