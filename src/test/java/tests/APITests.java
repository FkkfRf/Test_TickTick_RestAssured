package tests;

import base.BaseTestAPI;
import models.lombok.TaskBody;
import models.lombok.LoginBody;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static spec.RequestSpecs.loginRequestSpec;
import static spec.ResponseSpecs.loginSuccessResponseSpec;
import static spec.ResponseSpecs.loginUnSuccessResponseSpec;

public class APITests extends BaseTestAPI {

    @Test
    void loginSuccessTest() {

        LoginBody loginBody = new LoginBody();
        loginBody.setPassword(loginPassword);
        loginBody.setUsername(loginUsername);
        loginBody.setToken("");

        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginSuccessResponseSpec);
    }

    @Test
    void loginUnSuccessTest() {
        LoginBody loginBody = new LoginBody();
        loginBody.setPassword(loginPassword + " ");
        loginBody.setUsername(loginUsername + " ");
        loginBody.setToken("");

        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginUnSuccessResponseSpec);
    }

    @Test
    void createTaskTest() {

        LoginBody loginBody = new LoginBody();
        loginBody.setPassword(loginPassword);
        loginBody.setUsername(loginUsername);
        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginSuccessResponseSpec);

        String getAuthorizationCookie = String.valueOf(
                given()
                        .body(loginBody)
                        .get("https://ticktick.com/signin")
                        .then().extract().response()
                        .getCookies());

        System.out.println("Cookies: " + getAuthorizationCookie);

        String titleName;
        titleName = "Тестовая задача 5";

        TaskBody.ListTaskBody.ListAdd addBodyList = new TaskBody.ListTaskBody.ListAdd();
        addBodyList.setTitle(titleName);

        TaskBody.ListTaskBody taskBody = new TaskBody.ListTaskBody();
        taskBody.setAdd(addBodyList);

        given()
                .log().all()
                .cookie(getAuthorizationCookie)
                .body(taskBody)
                .contentType(JSON)
                .post("https://api.ticktick.com/api/v2/batch/task")
                .then()
                .log().all()
                .statusCode(200);
    }
}

