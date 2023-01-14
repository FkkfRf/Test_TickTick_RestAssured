package spec;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.*;

public class RequestSpecs {

    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .basePath("/api/v2/user/signon")
            .log().body()
            .log().uri()
            .contentType(JSON);

    public static RequestSpecification createTaskSpec = with()
            .filter(withCustomTemplates())
            .basePath("/api/v2/batch/task")
            .log().body()
            .log().uri()
            .contentType(JSON);
}
