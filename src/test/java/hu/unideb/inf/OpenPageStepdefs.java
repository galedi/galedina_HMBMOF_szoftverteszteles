package hu.unideb.inf;

import io.cucumber.java.en.Given;

public class OpenPageStepdefs extends StepDefinitions{

    @Given("The home page is opened")
    public void theHomePageIsOpened() {
        homePage = new HomePage(driver);
    }
}
