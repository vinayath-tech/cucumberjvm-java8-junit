package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;

    @FindBy(how= How.CSS, using =".login")
    private WebElement signInBtn;

    @FindAll(@FindBy(how=How.CSS, using="ul#homefeatured .product_img_link"))
    private List<WebElement> dresses;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage(String baseUrl){
        driver.get(baseUrl);
    }

    public void clickSignInBtn(){
        signInBtn.click();
    }

    public List<String> verifyDressList(){
        List<String> expDressText = new ArrayList<>();
        dresses.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .forEach(expDress -> expDressText.add(expDress));
        return expDressText;
    }
}
