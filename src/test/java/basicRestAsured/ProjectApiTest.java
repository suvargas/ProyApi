package basicRestAsured;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProjectApiTest {

    @Test
    @DisplayName("Verify the CRUD the project")
    public void verifyCreateUpdateDeleteProject(){

        //create
        JSONObject body = new JSONObject();
        body.put("Content","UCB_JsonObject");
        body.put("Icon",8);

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4)
                        .freeze()).freeze();


        Response response =given()
                .auth()
                .preemptive()
                .basic("ucb2023@ucb2023.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json");

        System.out.println(">>>>>>>>>>>> Response <<<<<<<<<<<<<");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_JsonObject"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaVerification2.json").using(schemaFactory))
                .log().all();

        // extraer una valor
        int idProject= response.then().extract().path("Id");

        //read
        response=given()
                .auth()
                .preemptive()
                .basic("ucb2023@ucb2023.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/projects/"+idProject+".json");

        System.out.println(">>>>>>>>>>>> Response <<<<<<<<<<<<<");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_JsonObject"))
                .log().all();

        // update
        body.put("Content","UCB_Update");
        response =given()
                .auth()
                .preemptive()
                .basic("ucb2023@ucb2023.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly/api/projects/"+idProject+".json");

        System.out.println(">>>>>>>>>>>> Response <<<<<<<<<<<<<");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Update"))
                .log().all();


        //delete
        response=given()
                .auth()
                .preemptive()
                .basic("ucb2023@ucb2023.com","12345")
                .log().all()
                .when()
                .delete("https://todo.ly/api/projects/"+idProject+".json");

        System.out.println(">>>>>>>>>>>> Response <<<<<<<<<<<<<");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Update"))
                .body("Deleted",equalTo(true))
                .log().all();


    }

}