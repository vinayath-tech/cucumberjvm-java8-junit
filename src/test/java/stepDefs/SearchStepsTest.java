package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataproviders.ConfigurationFileReader;
import managers.PageObjectManager;
import managers.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObject.HomePage;

import java.io.IOException;

public class SearchStepsTest {

    WebDriver driver;
    HomePage homePage;
    WebDriverManager webDriverManager;
    ConfigurationFileReader configFileReader;
    PageObjectManager pageObjectManager;

    public SearchStepsTest() throws IOException {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        configFileReader = new ConfigurationFileReader();
    }

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws Throwable {
        homePage.navigateToHomePage(configFileReader.getApplicationUrl());
    }

    @When("^I search for \"(.*?)\"$")
    public void i_search_for(String searchTxt) throws Throwable {
        homePage.enterSearchKeyword(searchTxt);
    }

    @Then("^I should see (\\d+) dress on results$")
    public void i_should_see_dress_on_results(long totDressResults) throws Throwable {
        Assert.assertEquals(homePage.totSearchResults(), totDressResults);
    }

    @Then("^the dress should be sorted by lowest price$")
    public void the_dress_should_be_sorted_by_lowest_price() throws Throwable {
        homePage.verifyProdsSortOrder();
    }
    
    @After
    public void tearDown(){
        webDriverManager.closeDriver();
    }

}
