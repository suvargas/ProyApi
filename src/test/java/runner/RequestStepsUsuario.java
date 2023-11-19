package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RequestStepsUsuario {

    Response response;
    String token;
    int idProject;
    @Given("que uso el token en todo.ly")
    public void queUsoElTokenEnTodoLy() {
        response=given()
                .auth()
                .preemptive()
                .basic("Susivargass@gmail.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/authentication/token.json");
        token = response.then().extract().path("TokenString");
    }


    @When("uso el token en todo.ly para user")
    public void queUsoElTokenEnTodoLyParaUser() {
        response=given()
                .auth()
                .preemptive()
                .basic("Susivargass@gmail.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/authentication/token.json");
        token = response.then().extract().path("TokenString");
    }
    @When("uso el token en todo.ly para usuario actualizado")
    public void UsoElTokenEnTodoLyParaUserActualizado() {
        response=given()
                .auth()
                .preemptive()
                .basic("Susivargass@gmail.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/authentication/token.json");
        token = response.then().extract().path("TokenString");
    }


    @When("envio un POST url {string} con body")
    public void envioUnPOSTConBody(String url,String body) {
        response =given()
                .header("Token",token)
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly"+url);
    }
    @When("envio un POST url para user {string} con body")
    public void envioUnPOSTConBodyy(String url, String body) {
        response =given()

                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly"+url);
    }
    @When("envio un PUT url {string} con body")
    public void envioUnPUTConBody(String url,String body ) {
        url = url.replace("IdProject",idProject+"");
        response =given()
                .header("Token",token)
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly"+url);
    }

    @When("envio un PUT url user {string} con body")
    public void envioUnPUTConBody1(String url,String body ) {

        response =given()
                .header("Token",token)
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly"+url);
    }

    @When("envio un DELETE url {string} con body")
    public void envioUnDELETEConBody(String url,String body ) {
        url = url.replace("IdProject",idProject+"");
        response =given()
                .header("Token",token)
                .log().all()
                .when()
                .delete("https://todo.ly"+url);
    }
    @When("envio un DELETE url user {string} con body")
    public void envioUnDELETEConBody1(String url,String body ) {
        response =given()
                .header("Token",token)
                .log().all()
                .when()
                .delete("https://todo.ly"+url);
    }

    @Then("mi codigo de respuesta es {int}")
    public void miCodigoDeRespuestaEs(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @And("mi atributo {string} deberia ser {string}")
    public void miAtributoDeberiaSer(String attribute, String expectedValue) {
        response.then().body(attribute,equalTo(expectedValue));
    }

    @And("guardo mi Id en la variable IdProject")
    public void guardoMiIdEnLaVariableIdProject() {
        idProject= response.then().extract().path("Id");
    }
}