package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import support.RestWrapper;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class RestStepDefs {

    @Given("I login via REST as {string}")

    public void iLoginViaRESTAs(String role) {
        new RestWrapper().login(getData(role));  //WHY GET DATA?        //RestWrapper rest = new RestWrapper(); rest.login(getData(role));
    }

    @When("I create via REST {string} position")

    public void iCreateViaRESTPosition(String type) {
        new RestWrapper().createPosition(getData(type));

    }

    @Then("I verify via REST new position is in the list")
    public void iVerifyViaRESTNewPositionIsInTheList() {

        List<Map<String, Object>> actualPositions = new RestWrapper().getPositions();
        Map<String, Object> lastPosition = new RestWrapper().getLastPosition();

        boolean isFound = false;

        for (Map<String, Object> actualPosition : actualPositions) {
            if (actualPosition.get("id").equals(lastPosition.get("id"))) {
                isFound = true;
                //    break;
                for (String key : lastPosition.keySet()) {
                    Object expected = lastPosition.get(key);
                    Object actual = actualPosition.get(key);
                    System.out.println("Veryfying field: " + key);
                    System.out.println("Expected: " + expected);
                    System.out.println("Actual: " + actual);
                    assertThat(actual).isEqualTo(expected);

                }


            }
        }
        assertThat(isFound).isTrue();

    }


    @When("I create via REST {string} candidate to the list")
    public void iCreateViaRESTCandidateToTheList(String type) {
        new RestWrapper().createPositionSdet(getData(type));




    }

    @Then("I verify via REST new candidate is in the list")
    public void iVerifyViaRESTNewCandidateIsInTheList() {



    }


}
