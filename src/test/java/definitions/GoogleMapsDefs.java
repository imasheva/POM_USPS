package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class GoogleMapsDefs {

 /*
    @Given("I perform get request")
    public void iPerformGetRequest() {

      // GET request

        //BaseURL or Host
        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "500").
                param("key", "AIzaSyDe_xY-aqTnSQDeCqs1zZUg7f2okTERFss").


        when().
                get("/maps/api/place/nearbysearch/json").  //provide resource before ?

        // program executes till here and stores in virtual storage
        //from here start basic assertions


        then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);
           //     and().
            //   body("results[0].geometry.location.lat", equalTo("-33.8688197"));


    }


    @When("a user performs a post request")
    public void aUserPerformsAPostRequest() {

        //POST request  *POST DATA*

        //BaseURL or Host

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

        //  3 types of parameters

        //  1. Form parameter
        //  2. Query parameter
        //  3. Header parameter

        given().
                queryParam("key", "qaclick123").
                body(b);

        when().
                post("/maps/api/place/add/json"). //method

                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);//and().body("status", equalTo("OK"));


        //  1. Create a place and 2.delete that
        //  Create a place  = response (place id)
        //   delete Place = (Request - Place id)

    }

    @And("a user add and deletes place")
    public void aUserAddAndDeletesPlace() {

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

        //Task 1 - Grab the response
        RestAssured.baseURI = "http://216.10.245.166";
        Response res = (Response) //create variable where response will be passes into the variable

                given().
                queryParam("key", "qaclick123").
                body(b).
       //method
        when().
                post("/maps/api/place/add/json"). //method
        //assertions
        then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).and().
                        body("status", equalTo("OK")).

       //extract
        extract().response();  // once hit that ->   Response res = (Response) //create variable where response will be passed into the variable

        //Task2 - Grab the Place ID from response
        String responseString = res.asString();
        System.out.println(responseString);
        //How to convert response we got into JSON?   -- JsonPath js = new JsonPath();

        JsonPath js = new JsonPath(responseString);
        String placeid = js.get("place_id");
    }*/

















}

