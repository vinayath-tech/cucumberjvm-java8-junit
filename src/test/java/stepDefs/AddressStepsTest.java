package stepDefs;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import dataproviders.ConfigurationFileReader;
import managers.PageObjectManager;
import managers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import pageObject.*;
import java.util.List;
import java.util.Map;

public class AddressStepsTest {
    WebDriver driver;
    AccountPage accountPage;
    HomePage homePage;
    LoginPage loginPage;
    WebDriverManager webDriverManager;
    ConfigurationFileReader configFileReader;
    PageObjectManager pageObjectManager;

    public AddressStepsTest() throws Exception {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        accountPage = pageObjectManager.getAccountPage();
        homePage = pageObjectManager.getHomePage();
        loginPage = pageObjectManager.getLoginPage();
        configFileReader = new ConfigurationFileReader();
    }

    @Given("^I am logged into the application$")
    public void i_am_logged_into_the_application() throws Throwable {
        homePage.navigateToHomePage(configFileReader.getApplicationUrl());
        homePage.clickSignInBtn();
        loginPage.enterUserCredentials();
    }

    @Given("^I navigate to add new address form$")
    public void i_navigate_to_add_new_address_form() throws Throwable {
        accountPage.navigateToAddressForm();
    }

    @Then("^I fill in the form using the following data:$")
    public void i_fill_in_the_form_using_the_following_data(DataTable dt) throws Throwable {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        String addressData = list.get(0).get("address");
        String cityData = list.get(0).get("city");
        String stateData = list.get(0).get("state");
        String pocodeData = list.get(0).get("pocode");
        String numberData = list.get(0).get("number");

        accountPage.fillAddress(addressData);
        accountPage.fillCity(cityData);
        accountPage.selectState(stateData);
        accountPage.fillPoCode(pocodeData);
        accountPage.fillPhone(numberData);
        accountPage.submitAddressForm();
    }

    @Then("^I should be able to successfully submit my details$")
    public void i_should_be_able_to_successfully_submit_my_details() throws Throwable {
        accountPage.verifyAddressSubmission();
    }

    @After
    public void tearDown(){
        webDriverManager.closeDriver();
    }
}
