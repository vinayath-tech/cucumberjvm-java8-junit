package pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
    WebDriver driver;

    @FindBy(how = How.CSS, using=".addresses-lists")
    private WebElement accountDetailEle;

    @FindBy(how=How.CSS, using="[title='Addresses']")
    private WebElement addressEle;

    @FindBy(how=How.CSS, using="[title='Add an address']")
    private WebElement addAddressEle;

    @FindBy(how=How.CSS, using="#submitAddress")
    private WebElement submitBtn;

    @FindBy(how=How.CSS, using="[name='address1']")
    private WebElement addressInput;

    @FindBy(how=How.CSS, using="[name='city']")
    private WebElement cityInput;

    @FindBy(how=How.CSS, using="[name='id_state']")
    private WebElement stateDrpDown;

    @FindBy(how=How.CSS, using="[name='phone']")
    private WebElement phoneInput;

    @FindBy(how=How.CSS, using="[name='postcode']")
    private WebElement poCode;

    @FindBy(how=How.CSS, using="#center_column>div.addresses>div>div:nth-child(2)>ul>li.address_update>a:nth-child(2)")
    private WebElement deleteBtn;

    public AccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElement(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public boolean verifyAccountsSection(){
        waitForElement(accountDetailEle);
        return accountDetailEle.isDisplayed();
    }

    public void navigateToAddressForm(){
        waitForElement(addressEle);
        addressEle.click();

        waitForElement(addAddressEle);
        addAddressEle.click();

        waitForElement(submitBtn);
    }

    public void fillAddress(String addressTestData){
        addressInput.sendKeys(addressTestData);
    }

    public void fillCity(String cityTestData){
        cityInput.sendKeys(cityTestData);
    }

    public void selectState(String drpDwnData){
        Select stateDropDown = new Select(stateDrpDown);
        stateDropDown.selectByVisibleText(drpDwnData);
    }

    public void fillPhone(String phoneData){
        phoneInput.sendKeys(phoneData);
    }

    public void fillPoCode(String poData){
        poCode.sendKeys(poData);
    }

    public void submitAddressForm(){
        submitBtn.click();
    }

    public void verifyAddressSubmission() {
        waitForElement(deleteBtn);
        deleteBtn.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
