package stepDefs;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataproviders.ConfigurationFileReader;
import managers.PageObjectManager;
import managers.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObject.*;

import java.util.List;

public class DressStepsTest {
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    WebDriverManager webDriverManager;
    ConfigurationFileReader configFileReader;
    PageObjectManager pageObjectManager;

    public DressStepsTest() throws Exception {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        productPage = pageObjectManager.getProductPage();
        configFileReader = new ConfigurationFileReader();
    }

    @Given("^I navigate to the dress categories$")
    public void i_navigate_to_the_dress_categories() throws Throwable {
        homePage.navigateToHomePage(configFileReader.getApplicationUrl());
    }

    @Then("^I should see the list of latest collections$")
    public void i_should_see_the_list_of_latest_collections() throws Throwable {
        Assert.assertEquals(homePage.verifyDressList().size(), 7);
    }

    @When("^I view the \"(.*?)\"$")
    public void i_view_the(String arg1) throws Throwable {
        productPage.clickAProduct();
    }

    @Then("^the dress should be available in following colors:$")
    public void the_dress_should_be_available_in_following_colors(DataTable dt) throws Throwable {
       List<String> data = dt.asList(String.class);
       data.forEach((text) -> Assert.assertTrue(productPage.fetchColorOptions(text).isDisplayed()));
    }

    @Given("^I am on the detail page of the dress$")
    public void i_am_on_the_detail_page_of_the_dress() throws Throwable {
       homePage.navigateToHomePage("http://automationpractice.com/index.php?id_product=7&controller=product");
    }

    @When("^I select \"(.*?)\" color$")
    public void i_select_color(String arg1) throws Throwable {
        productPage.selectDressColor("Yellow");
    }

    @Then("^I should see (\\d+) dresses available with yellow color$")
    public void i_should_see_dresses_available_with_yellow_color(int arg1) throws Throwable {
        Assert.assertEquals(productPage.totImages(), 2);
    }

    @After
    public void tearDown(){
        webDriverManager.closeDriver();
    }
}
