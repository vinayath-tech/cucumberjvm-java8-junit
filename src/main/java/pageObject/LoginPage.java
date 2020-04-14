package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    @FindBy(how = How.CSS, using="#email")
    private WebElement emailInput;

    @FindBy(how = How.CSS, using="#passwd")
    private WebElement passwordInput;

    @FindBy(how = How.CSS, using="#SubmitLogin")
    private WebElement submitBtn;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElement(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void enterUserCredentials() {
        waitForElement(emailInput);
        emailInput.sendKeys("gok@test.com");
        passwordInput.sendKeys("Test123");
        submitBtn.click();
    }
}
