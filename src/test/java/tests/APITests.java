package tests;

import base.BaseTestAPI;
import models.lombok.TaskBody;
import models.lombok.LoginBody;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static api.AuthorizationApi.ALLURE_TESTOPS_SESSION;
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

    @Test
    void createTaskTest(){

        /*
        1. https://api.ticktick.com/api/v2/batch/task
        body {"add":[{"items":[],"reminders":[],"exDate":[],"dueDate":null,"priority":0,"isAllDay":true,"progress":0,"assignee":null,"sortOrder":-4398046511104,"startDate":"2023-01-13T21:00:00.000+0000","isFloating":false,"status":0,"projectId":"inbox120897079","kind":null,"createdTime":"2023-01-14T16:24:38.000+0000","modifiedTime":"2023-01-14T16:24:38.000+0000","title":"Тестовая задача ","tags":[],"timeZone":"Europe/Moscow","content":"","id":"63c2d746a1aa525fe15a0e3f"}],"update":[],"delete":[],"addAttachments":[],"updateAttachments":[],"deleteAttachments":[]}
        2."id": "63c2d746a1aa525fe15a0e3f"
        {"id2etag":{"63c2d746a1aa525fe15a0e3f":"9crkdwdp"},"id2error":{}}
         */

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
                .cookie("_ga=GA1.2.129246384.1673651176")
                .body(taskBody)
                .contentType(JSON)
                //.queryParam("projectId", "1771")
                //testcase/13917/scenario
                .post("https://api.ticktick.com/api/v2/batch/task")
                .then()
                .log().body()
                .statusCode(200);
    }
}

