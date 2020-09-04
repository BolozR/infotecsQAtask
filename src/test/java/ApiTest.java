import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.equalTo;


public class ApiTest {
    private int id;
    private Configuration configuration;

    @BeforeSuite
    public void setConfiguration(){
        this.configuration = new Configuration();
    }

    @Test(priority = 0)
    public void testCreate(){
        Response response = given()
                .log().all()
                .contentType("application/json").body("{\"name\": \"" + configuration.getName() + "\",\n" +
                        "\"job\": \"" + configuration.getPosition() + "\"}")
                .when()
                .post(configuration.getBaseURL() + "/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo(configuration.getName()))
                .body("job", equalTo(configuration.getPosition()))
                .extract()
                .response();
        response.getBody().print();
        this.id = response.getBody().jsonPath().getInt("id");
    }

    @Test(priority = 1)
    public void testGet(){
        int total = 0;
        Response response = given().
                when().get(configuration.getBaseURL() + "/api/users")
                .then()
                .statusCode(200).extract().response();
        total = response.getBody().jsonPath().getInt("total");

        response = given()
                .log().all()
                .when().get(configuration.getBaseURL() + "/api/users?per_page={total}", total)
                .then()
                .statusCode(200)
                .body("total", equalTo(total))
                .body("data.first_name", hasItem(configuration.getName()))
                .extract()
                .response();
        response.getBody().print();
    }


    @Test(priority = 2)
    public void testPut(){
        Response response = given()
                .log().all()
                .contentType("application/json").body("{\"name\": \"" + configuration.getName() + "\",\n" +
                        "\"job\": \"" + configuration.getSecondPosition() + "\"}")
                .when()
                .put(configuration.getBaseURL() + "/api/users/{id}", this.id)
                .then()
                .statusCode(200)
                .body("name", equalTo(configuration.getName()))
                .body("job", equalTo(configuration.getSecondPosition()))
                .extract()
                .response();
        response.getBody().print();
    }

    @Test(priority = 3)
    public void testDelete(){
        Response response = given()
                .log().all()
                .when()
                .delete(configuration.getBaseURL() + "/api/users/{id}", this.id)
                .then()
                .statusCode(204)
                .extract()
                .response();
    }

}
