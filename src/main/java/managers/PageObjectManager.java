package managers;

import org.openqa.selenium.WebDriver;
import pageObject.AccountPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductPage;


public class PageObjectManager {

    public WebDriver driver;
    private AccountPage accountPage;
    private HomePage homePage;
    private LoginPage loginPage;
    private ProductPage productPage;

    public PageObjectManager(WebDriver driver){
        this.driver = driver;
    }

    public AccountPage getAccountPage(){
        return (accountPage == null) ? accountPage = new AccountPage(driver) : accountPage;
    }

    public HomePage getHomePage(){
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    public LoginPage getLoginPage(){
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public ProductPage getProductPage(){
        return (productPage == null) ? productPage = new ProductPage(driver) : productPage;
    }
}
