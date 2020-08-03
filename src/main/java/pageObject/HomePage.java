package pageObject;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HomePage {
    WebDriver driver;

    @FindBy(how= How.CSS, using =".login")
    private WebElement signInBtn;

    @FindAll(@FindBy(how=How.CSS, using="ul#homefeatured .product_img_link"))
    private List<WebElement> dressLinks;

    @FindAll(@FindBy(how=How.CSS, using="li.ajax_block_product"))
    private List<WebElement> searchResLinks;

    @FindAll(@FindBy(how=How.CSS, using="ul#homefeatured a.product-name"))
    private List<WebElement> dressNames;

    @FindAll(@FindBy(how=How.CSS, using="#homefeatured .right-block"))
    private List<WebElement> dressPrice;

    @FindBy(how=How.CSS, using="ul.product_list")
    private WebElement gridList;

    @FindBy(how=How.CSS, using="#homefeatured a.product_img_link")
    private WebElement productImage;

    @FindBy(how=How.CSS, using="#homefeatured [title='Add to cart']")
    private WebElement cartBtn;

    @FindBy(how=How.CSS, using="[title='Continue shopping']")
    private WebElement continueBtn;

    @FindBy(how=How.CSS, using="[title='View my shopping cart'] .ajax_cart_quantity")
    private WebElement totCartProd;

    @FindBy(how=How.CSS, using="#search_query_top")
    private WebElement searchField;

    @FindBy(how=How.XPATH, using="//button[@name='submit_search']")
    private WebElement searchBtn;

    @FindAll(@FindBy(how=How.CSS, using=".right-block .price.product-price"))
    private List<WebElement> searchProdPrice;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElement(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void navigateToHomePage(String baseUrl){
        driver.get(baseUrl);
    }

    public void clickSignInBtn(){
        signInBtn.click();
    }

    public long verifyDressList(){
        long totalDresses = dressLinks.stream()
                                      .filter(WebElement::isDisplayed)
                                      .map(WebElement::getText)
                                      .count();
        return totalDresses;
    }

    public long checkDressAvailability(Predicate<? super WebElement> predicate){
        long totDressType = dressNames.stream()
                                      .filter(predicate)
                                      .count();
        return totDressType;
    }

    public void fetchProdPrice(int prodPrice){
        Actions action = new Actions(driver);
        dressPrice.forEach(i-> {
            Integer j = (int) Double.parseDouble(i.findElement(By.cssSelector(".price.product-price")).getText().replace("$",""));
            if(Math.round(j) < prodPrice){
                action.moveToElement(i).perform();
                cartBtn.click();
                if(continueBtn.isDisplayed()){
                    continueBtn.click();
                }
            }
        });
    }

    public String getProdTot(){
        waitForElement(totCartProd);
        return totCartProd.getText();
    }

    public void enterSearchKeyword(String searchTxt){
        if(searchField.isDisplayed()){
            searchField.sendKeys(searchTxt);
            searchBtn.click();
            waitForElement(gridList);
        }
    }

    public long totSearchResults(){
        long searchResults = searchResLinks.stream()
                                           .filter(WebElement::isDisplayed)
                                           .count();
        return searchResults;
    }

    public void verifyProdsSortOrder() throws Exception {
        List<Integer> prodPrice = searchProdPrice.stream()
                .map(i -> (int) Double.parseDouble(i.getText().replace("$", "")))
                .collect(Collectors.toList());

        if(!Ordering.natural().isOrdered(prodPrice)){
            throw new Exception("The results are not sorted in ascending order");
        }
    }


}
