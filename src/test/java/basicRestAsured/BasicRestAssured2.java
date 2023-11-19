package basicRestAsured;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class BasicRestAssured2 {

    @Test
    public void verifyCreateProjectJsonObject(){
        JSONObject body = new JSONObject();
        body.put("Content","UCB_JsonObject");
        body.put("Icon",8);


        given()
                .auth()
                .preemptive()
                .basic("ucb2023@ucb2023.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .statusCode(200)
                .body("Content",equalTo("UCB_JsonObject"))
                .log().all();

    }

    @Test
    public void verifyCreateProjectSchemaCheck(){
        JSONObject body = new JSONObject();
        body.put("Content","UCB_JsonObject");
        body.put("Icon",8);

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4)
                        .freeze()).freeze();


        given()
                .auth()
                .preemptive()
                .basic("ucb2023@ucb2023.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .statusCode(200)
                .body("Content",equalTo("UCB_JsonObject"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaVerification2.json").using(schemaFactory))
                .log().all();

    }
}