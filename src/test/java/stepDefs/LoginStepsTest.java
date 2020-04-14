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
import pageObject.AccountPage;
import pageObject.HomePage;
import pageObject.LoginPage;


public class LoginStepsTest {
    WebDriver driver;
    HomePage homePage;
    AccountPage accountPage;
    LoginPage loginPage;
    WebDriverManager webDriverManager;
    ConfigurationFileReader configFileReader;
    PageObjectManager pageObjectManager;

    public LoginStepsTest() throws Exception {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        accountPage = pageObjectManager.getAccountPage();
        loginPage = pageObjectManager.getLoginPage();
        configFileReader = new ConfigurationFileReader();
    }

    @Given("^I am on login page$")
    public void i_am_on_login_page() throws Throwable {
       homePage.navigateToHomePage(configFileReader.getApplicationUrl());
    }

    @When("^I login with valid user credentials$")
    public void i_login_with_valid_user_credentials() throws Throwable {
        homePage.clickSignInBtn();
        loginPage.enterUserCredentials();
    }

    @Then("^I should be able to successfully login$")
    public void i_should_be_able_to_successfully_login() throws Throwable {
        Assert.assertTrue(accountPage.verifyAccountsSection());
    }

    @After
    public void tearDown(){
        webDriverManager.closeDriver();
    }
}
