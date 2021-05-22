package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.core.StringContains;
import org.junit.Assert;

public class CartStepdefs extends StepDefinitions {

    private static Float cartProductTotal;

    @Given("Item has been added to the cart")
    public void anItemHasBeenAddedToTheCart() {
        homePage.clickFirstPopularProductAddToCartButton();
    }


    @When("The cart button is clicked")
    public void theCartButtonIsClicked() {
        homePage.clickCartButton();
    }

    @Then("The cart page loads")
    public void theCartPageLoads() {
        String title = homePage.getCurentPageTitle();
        Assert.assertThat(title, StringContains.containsString("SHOPPING-CART SUMMARY"));
        cartProductTotal = homePage.getCurentCartProductTotal();
    }
    @And("One product is displayed in a row in the cart")
    public void oneProductIsDisplayedInARowInTheCart() {
        Assert.assertEquals(homePage.getCurentProductCount(), new Integer(1));
    }

    @Then("The item remove button is pressed")
    public void theItemRemoveButtonIsPressed() {
        homePage.cartDeleteItem();
    }

    @And("The cart should be empty")
    public void theCartShouldBeEmpty() {
        Assert.assertEquals(new String("Your shopping cart is empty."), homePage.getAlertText());
    }

    @Then("Reset")
    public void resetSession() {
        homePage.resetSession();
    }
}
