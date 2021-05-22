package hu.unideb.inf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchStepdefs extends StepDefinitions {


    @Given("The search button is clicked")
    public void theSearchButtonIsClicked() {

        homePage.clickSearchButton();

    }

    @Then("Please enter search keyword message is shown")
    public void pleaseEnterSearcKeywordMessageIsShown() {
    }

    @Given("The user enters the {string} in {string}")
    public void theUserEntersTheValue(String value, String field) {
        homePage.fieldFill(field, value);
    }

    @Then("The {string} is shown")
    public void theMessageIsShown(String message) {
    }

}
