package runner;

import config.Configuracion;
import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

public class RequestStepClean {
    Response response;
    RequestInfo requestInfo=new RequestInfo();
    Map<String,String> variables = new HashMap<>();

    @Given("using token in todo.ly")
    public void usingTokenInTodoLy() {
        // request
        String credentials = Base64.getEncoder()
                .encodeToString((Configuracion.user+":"+Configuracion.password).getBytes());

        requestInfo.setUrl(Configuracion.host+"/api/authentication/token.json")
                .setHeader("Authorization","Basic "+credentials);
        response = FactoryRequest.make("get").send(requestInfo);
        // get token
        String token = response.then().extract().path("TokenString");
        requestInfo = new RequestInfo();
        requestInfo.setHeader("Token",token);
    }

    @When("send {} request {string} with body")
    public void sendPOSTRequestWithBody(String method,String url,String body) {
        requestInfo.setUrl(Configuracion.host+this.replaceValues(url)).setBody(body);
        response = FactoryRequest.make(method).send(requestInfo);
    }

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @And("the attribute {string} should be {string}")
    public void theAttributeShouldBe(String attribute, String expectedValue) {
        response.then().body(attribute,equalTo(expectedValue));
    }

    @And("save {string} in the variable {string}")
    public void saveInTheVariable(String attribute, String nameVariable) {
        variables.put(nameVariable,response.then().extract().path(attribute)+"");
    }
    private String replaceValues(String value){
        for (String key:variables.keySet() ) {
            value=value.replace(key,variables.get(key));
        }
        return value;
    }
}
