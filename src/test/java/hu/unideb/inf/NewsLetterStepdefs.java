package hu.unideb.inf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class NewsLetterStepdefs extends StepDefinitions {

    @Given("User Click news letter subscription button")
    public void clickSubscriptionButton() {
        homePage.clickNewsLetterButton();
    }

    @Then("Newsletter : Invalid email address. error message is shown")
    public void newsLetterInvalidEmailAddressErrorShow() {
        assertEquals("Newsletter : Invalid email address.", homePage.getNewsLetterMessage());
    }

    @Given("User enters invalid email")
    public void userEntersInvalidEmail() {
        homePage.fillField("newsletter-input", "invalid.email.com");
    }

    @Given("User enters valid email")
    public void userEntersValidEmail() {
        homePage.fillField("newsletter-input", UUID.randomUUID() + "@email.com");
    }

    @Then("Newsletter : You have successfully subscribed to this newsletter.")
    public void newsLetterSuccessEmailAddressShow() {
        assertEquals("Newsletter : You have successfully subscribed to this newsletter.", homePage.getNewsLetterMessage());
    }
}
