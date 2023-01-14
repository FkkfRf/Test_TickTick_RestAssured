package tests;

import base.BaseTestAPI;
import models.lombok.LoginBody;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static spec.RequestSpecs.loginRequestSpec;
import static spec.ResponseSpecs.loginSuccessResponseSpec;
import static spec.ResponseSpecs.loginUnSuccessResponseSpec;

public class APITests extends BaseTestAPI {

    @Test
    void loginSuccessTest() {

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

    }

    @Test
    void loginUnSuccessTest() {
        LoginBody loginBody = new LoginBody();
        loginBody.setPassword(loginPassword + " ");
        loginBody.setUsername(loginUsername + " ");

        given()
                .spec(loginRequestSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginUnSuccessResponseSpec);
    }
}

