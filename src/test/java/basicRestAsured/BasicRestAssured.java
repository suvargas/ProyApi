package basicRestAsured;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class BasicRestAssured {

    @Test
    public void verifyCreateProject(){
        given()
                .auth()
                .preemptive()
                .basic("ucb2023@ucb2023.com","12345")
                .body("{\n" +
                        " \"Content\":\"RESTASSURED\",\n" +
                        " \"Icon\":5\n" +
                        "}")
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .log().all();

    }


    @Test
    public void verifyCreateProjectExternalFile(){
        String pathFile=new File("").getAbsolutePath() +"/src/test/resources/body.json";
        given()
                .auth()
                .preemptive()
                .basic("ucb2023@ucb2023.com","12345")
                .body(new File(pathFile))
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .log().all();

    }


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
                .log().all();

    }


}
