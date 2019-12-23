package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GoogleMaps2Stepdefs {
    @Given("I perform get request")
    public void iPerformGetRequest() {

        //1 . RestAssured method "." + baseURI


        RestAssured.baseURI = "https://maps.googleapis.com";

        //given().                        base url+ resources + ? + parameters
        // request headers
        //request parameters
        //request cookies
        //body

         given().
                 param("location", "-33.8670522,151.1957362").
                 param("radius", "500").
                 param("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").

    /*header()
      cookie()
      body()
     */

    //2nd. pass your request type in when()

        when().
                 //pass resource in get
            get("/maps/api/place/nearbysearch/json").

        then().
                 assertThat().
                 statusCode(200).and().
                 contentType(ContentType.JSON).and().
              //   body("results[0].geometry.location.lat", equalTo("-33.86882")).and().
                 body("results[0].name", equalTo("Sydney")).and().
                 body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
                 header("Server", "scaffolding on HTTPServer2");

        /*
        Assertions
        1. Status Code - verify
        2. Content Type  -verify
        3. Body response -verify
        4. Header response - verify

         */


    }

    @When("a user performs a post request")
    public void aUserPerformsAPostRequest() {

        //we are creating a place in googlemaps

        RestAssured.baseURI = "http://216.10.245.166";

        String b = " {"+

                "   \"location\" : { " +
                "   \"lat\" : -38.383494, " +
                "    \"lng\" : 151.1958750 " +
                "  },"+
                "    \"accuracy\" : 50, "+
                "    \"name\" : \"Google Shoes\","+
                "    \"phone_number\" : \"(+91) 983 893 3937\","+
                "    \"address\" : \"29, side layout, cohen 09\","+
                "    \"types\" : [\"shoe park\", \"shop\"],"+
                "    \"website\" : \"http://google.com\","+
                "    \"language\" : \"French-IN\""+
                "  }";

        Response res =  given().
                queryParam("key", "qaclick123"). //query param contains ?
                body(b).
          when().
          post("/maps/api/place/add/json").

          then().
                  assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                  body("status", equalTo("OK")).
           extract().response();

        String responseString = res.asString();


        //Create a place  API           = response (place id)
        // and delete the place  API    = request - place id














    }
}
