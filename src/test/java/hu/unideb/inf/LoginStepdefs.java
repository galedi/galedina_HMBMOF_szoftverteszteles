package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.fail;

public class LoginStepdefs extends StepDefinitions{


    @And("The Sign In link is clicked")
    public void theSignInLinkIsClicked() {
        homePage.clickSignInLink();
    }

    @When("the Sign In button is clicked")
    public void theSignInButtonIsClicked() {
        homePage.clickSignInButton();
    }

    @Given("the {string} is filled with {string}")
    public void theFieldIsFilledWithParameter(String field, String parameter) {
        homePage.fillField(field, parameter);
    }

    @Then("the {string} error message is shown")
    public void theMsgErrorMessageIsShown(String msg) {
       Optional<String> errorMessage = homePage.getErrorMessage();
        if (errorMessage.isPresent()) {
            Assert.assertEquals(msg, errorMessage.get());
        } else {
            fail();
        }
    }

    @Given("Create account is clicked")
    public void createAccountIsClicked() {
        homePage.clickCreateAnAccount();
    }

    @Then("The email address field is field a valid email")
    public void theEmailAddressFieldIsFieldWithRandomValidEmail() {
        homePage.fillField("email_create", UUID.randomUUID() + "@valid.hu");
    }

    @Then("I click Create an account")
    public void clickCreateAnAccount() {
        homePage.clickCreateAnAccount();
    }

    @Then("an Invalid email address error message is show")
    public void invalidEmailAddressErrorMessageIsShow() {
        Optional<String> errorMessage = homePage.getCreateAccountErrorMessage();
        if (errorMessage.isPresent()) {
            Assert.assertEquals("Invalid email address.", errorMessage.get());
        } else {
            Assert.fail();
        }
    }


}
