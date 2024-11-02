package stepDefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import pages.HomePage;

public class StepDefinitions {

    HomePage page = new HomePage();

    @Given("User goes to website")
    public void user_goes_to_website() {
        page.getTheUrl();
    }

    @Given("User verifies the logo is displayed")
    public void user_verifies_the_logo_is_displayed() {
        Assert.assertTrue(page.logo.isDisplayed());
    }

    @Given("User verifies that the title is {string}")
    public void user_verifies_that_the_title_is(String expectedTitle) {
        page.getTitle(expectedTitle);
    }

    @Given("Click")
    public void click() throws InterruptedException {

        page.clickNow();

    }

}
