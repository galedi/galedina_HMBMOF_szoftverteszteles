package hu.unideb.inf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class NavigateStepdefs extends StepDefinitions {

    @When("the {string} link is clicked")
    public void theNavigateLinkIsClicked(String button) {
        homePage.theLinkIsClicked(button);
    }

    @Then("the {string} is loaded")
    public void theUrlIsLoaded(String url) {

       homePage.getUrl(url);
    }
}
