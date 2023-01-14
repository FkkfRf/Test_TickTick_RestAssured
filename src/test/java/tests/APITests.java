package tests;

import base.BaseTestAPI;
import models.lombok.TaskBody;
import models.lombok.LoginBody;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

import static api.AuthorizationApi.ALLURE_TESTOPS_SESSION;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.authentication;
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
    void createTaskTest(){

        LoginBody loginBody = new LoginBody();
        loginBody.setPassword(loginPassword);
        loginBody.setUsername(loginUsername);
        loginBody.setToken("154BB8FE91446783544A0917730F3292E1C1CDC1FAEF86E0FF912404F0E9B177CA7DB1D798A7404169406594F7F54594B8CBE409528571FB6238D1D068EB4B228477188A5A4DE60C676CDE0406994D9A79D656DC584E7550B0EE545D9EED946F82BEE463B1431075BC4DD36207DCA5A879D656DC584E7550CEAD8B17EA1E23D477A05874A7177D5AD50B2A812E71759346C1E65D8CBB61E321FC16360D2CDBDA;");

        String AUTH_AWSALBCORS = "AWSALBCORS";
        String getAuthorizationCookie = String.valueOf(given()
                .body(loginBody)
                .get("https://ticktick.com/signin")
                .then().extract().response()
                  .getCookie(AUTH_AWSALBCORS));

        String AUTH_COOKIE = "AWSALB=" + getAuthorizationCookie + "; AWSALBCORS=" + getAuthorizationCookie + "; t=154BB8FE91446783544A0917730F3292E1C1CDC1FAEF86E0FF912404F0E9B177CA7DB1D798A7404169406594F7F54594B8CBE409528571FB6238D1D068EB4B228477188A5A4DE60C676CDE0406994D9A79D656DC584E7550B0EE545D9EED946F82BEE463B1431075BC4DD36207DCA5A879D656DC584E7550CEAD8B17EA1E23D477A05874A7177D5AD50B2A812E71759346C1E65D8CBB61E321FC16360D2CDBDA;";;
        System.out.println(AUTH_COOKIE);


        String titleName;
        titleName = "Тестовая задача 5";

        List<TaskBody.ListTaskBody> taskBodyArrayList = new ArrayList<>();
        List<TaskBody.ListTaskBody.ListAdd> addArrayList = new ArrayList<>();

        TaskBody.ListTaskBody.ListAdd addBodyList = new TaskBody.ListTaskBody.ListAdd();
        addBodyList.setTitle(titleName);

        TaskBody.ListTaskBody taskBody = new TaskBody.ListTaskBody();
        taskBody.setAdd(addBodyList);


    given()
                .log().all()
                .cookie("AWSALB" , getAuthorizationCookie)
                .body(taskBody)
                .contentType(JSON)
                .post("https://api.ticktick.com/api/v2/batch/task")
                .then()
                .log().all()
                .statusCode(200);
    }
}

