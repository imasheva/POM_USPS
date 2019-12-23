package support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RestWrapper {

    private String baseUrl = "https://skryabin.com/recruit/api/v1/";

    private static String loginToken;

    private static Map<String, Object> lastPosition;

    public static final String CONTENT_TYPE = "Content-Type"; //constant name - all caps
    public static final String JSON = "application/json";


    //for creating position step2
    public static final String AUTH = "Authorization";


    public static Map<String, Object> getLastPosition(){
        return lastPosition;
    }


    public void login(Map<String, String> credentials) {

        // prepare request

        RequestSpecification request = RestAssured

                .given()
                .log().all()
                .baseUri(baseUrl)
                .header(CONTENT_TYPE, JSON)
                //  .header(AUTH, loginToken)
                .body(credentials);

        // execute request

        Response response = request.post("login");  //   http:....//login  body->credentials   {email:"owen@example.com, password:"welcome"}

        // verify and extract

        Map<String, Object> result = response

                .then()
                .log().all()

                // BASIC VERIFICATION

                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

        loginToken = "Bearer " + result.get("token");      // get and save token

    }

    //support creation of position
    public Map<String, Object> createPosition(Map<String, String> position) {


        String dateOpen = position.get("dateOpen");
        String isDateOpen = new SimpleDateFormat("yyyy-MM-dd").format(new Date(dateOpen));
        position.put("dateOpen", isDateOpen);

        String title = position.get("title");
        String uniqueTitle = title + new SimpleDateFormat("-yyyy-MM-dd--HH-mm-sss").format(new Date());
        position.put("title", uniqueTitle);

        //preapre
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .header(CONTENT_TYPE, JSON) // .header("another key", "value")
                .header(AUTH, loginToken)
                .body(position);

        //execute
        Response response = request
                .when()
                .post("positions");

        //verify and extract
        Map<String, Object> result = response
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getMap("");

        lastPosition = result;

        return result;

    }

    public List<Map<String, Object>> getPositions() {

        //prepare

        RequestSpecification request = RestAssured
                .given()
                .baseUri(baseUrl)
                .log().all();

        //execute
        Response response = request
                .when()
                .get("positions");

        //verify and extract
        List<Map<String, Object>> result = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");

        return result;
    }

    public Map<String, Object> createPositionSdet  (Map<String, String> position) {
        String dateOpen = position.get("dateOpen");
        String isDateOpen = new SimpleDateFormat("-yyyy-MM-dd").format(new Date(dateOpen));

        String title = position.get("title");
        String uniqueTitle = title + new SimpleDateFormat("-yyyy-MM-dd-HH-mm-sss").format(new Date());

        position.put("title", uniqueTitle);

        //prepare

        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(position);

        //execute

        Response response = request
                .post("positions");    //endpoint

        //verify

        Map<String, Object> result = response
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getMap("");

        lastPosition = result;
        return result;
    }
}