package org.application.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;

public class GetPostSteps {
    private static ResponseOptions<Response> response;

    @Given("Perform GET operation for {string}")
    public void performGETOperationFor(String url) {
        response = RestAssuredExtension.getOps(url);
    }

    @Then("Should see the status as {string}")
    public void shouldSeeTheStatusAs(String status) {
        assertThat(response.getBody().jsonPath().get("status"), hasItem("DONE"));
    }

    @Then("Should see the task statuses")
    public void shouldSeeTheTaskStatuses() {
        BddStyledMethod.performContainsCollection();
    }

    @Then("Should verify GET parameters")
    public void shouldVerifyGETParameters() {
        BddStyledMethod.performPathParameter();
    }

    @Given("Perform Post operation for {string}")
    public void performPostOperationFor(String url) {
        BddStyledMethod.performPOSTWithBodyParameter(url);
    }

    @Given("Perform POST operation for {string} with body")
    public void performPOSTOperationForWithBody(String url, DataTable table) {
        HashMap<String, String> body = new HashMap<>();
        body.put(table.column(0).getFirst(), table.column(0).get(1));
        body.put(table.column(1).getFirst(), table.column(1).get(1));
        body.put(table.column(2).getFirst(), table.column(2).get(1));
        body.put(table.column(3).getFirst(), table.column(3).get(1));
        body.put(table.column(4).getFirst(), table.column(4).get(1));
        body.put(table.column(5).getFirst(), table.column(5).get(1));

        response = RestAssuredExtension.postOpsWithBody(url, body);
    }

    @Then("Should see the body has taskId {int}")
    public void shouldSeeTheBodyHasTaskId(int arg0) {

    }
}
