package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Users;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class inRhytmApi {
    String url = "https://reqres.in/api/users?page=2";


    @Test
    public void ListUsers() {
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println(response.statusCode());

        //print response body
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json; charset=utf-8", response.contentType());

        response.prettyPrint();

    }

    @Test
    public void SingleUser() {

        String url = "https://reqres.in/api/users/2";

        Response response = RestAssured.get(url);

        //print response status code
        System.out.println(response.statusCode());

        //print response body
        assertEquals(200, response.statusCode());

        response.prettyPrint();

    }

    @Test
    public void SingleUserNotFound() {


        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users/23");

        // Get the status code from the Response. In case of
        // a successfull interaction with the web service, we
        // should get a status code of 200.
        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        assertEquals(statusCode /*actual value*/, 404 /*expected value*/, "Correct status code returned");
    }

    @DisplayName("POST with Map to Users Class")
    @Test
    public void postMethod3(){
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();

        //create one object from your pojo, send it as a JSON
        Users user = new Users();
        user.setName("morpheus");
        user.setJob("leader");

        System.out.println("user = " + user);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(user).log().all()
                .when()
                .post("/api/users");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json; charset=utf-8"));


       // assertThat(response.path("data.name"),is("morpheus"));
       // assertThat(response.path("data.job"),is("leader"));

        response.prettyPrint();

    }
    @Test
    void test() {

        RestAssured.baseURI = "https://reqres.in/api/users";


        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name","morpheus");
        requestJsonMap.put("job","zion resident");
        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(requestJsonMap).log().all()
                .when()
                .put("/api/users/2");

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json; charset=utf-8"));

        assertThat(response.path("name"),is("morpheus"));
        assertThat(response.path("job"),is("zion resident"));


        response.prettyPrint();

    }
    @Test
    public void testDelete(){
        RestAssured.baseURI = "https://reqres.in/api/users/2";
        given()
                .and().accept(ContentType.JSON)
                .and()
                .delete("/api/users/2")
                .then()
                .statusCode(204)
                .log().body();
    }

    @Test
    public void postMethod4(){
        RestAssured.baseURI = "https://reqres.in/api/register";


        //create one object from your pojo, send it as a JSON
        Users user = new Users();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");

        System.out.println("user = " + user);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(user).log().all()
                .when()
                .post("/api/register");

        //verify status code
        //status code 201
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json; charset=utf-8"));


        // assertThat(response.path("data.name"),is("morpheus"));
        // assertThat(response.path("data.job"),is("leader"));

        response.prettyPrint();

    }

    @Test
    public void postMethod5(){
        RestAssured.baseURI = "https://reqres.in/api/register";


        //create one object from your pojo, send it as a JSON
        Users user = new Users();
        user.setEmail("email: sydney@fife");

        System.out.println("user = " + user);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(user).log().all()
                .when()
                .post("/api/register");

        //verify status code
        //status code 201
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json; charset=utf-8"));


        // assertThat(response.path("data.name"),is("morpheus"));
        // assertThat(response.path("data.job"),is("leader"));

        response.prettyPrint();

    }



}





