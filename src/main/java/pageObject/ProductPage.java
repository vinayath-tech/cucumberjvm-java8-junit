package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage {
    WebDriver driver;

    @FindBy(how= How.XPATH, using="//*[@id='homefeatured']/li[7]/div/div[1]/div/a[1]/img")
    private WebElement productLink;

    @FindBy(how=How.CSS, using=".primary_block")
    private WebElement quickViewContainer;

    @FindAll(@FindBy(how=How.CSS, using="#thumbs_list_frame li[style='display: list-item;']"))
    private List<WebElement> thumbList;



    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElement(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void clickAProduct(){
        productLink.click();
        waitForElement(quickViewContainer);
    }

    public WebElement fetchColorOptions(String prodColor){
        return driver.findElement(By.cssSelector("[title="+prodColor+"]"));
    }

    public void selectDressColor(String color) throws InterruptedException {
        driver.findElement(By.cssSelector("[name="+color+"]")).click();
        Thread.sleep(2000);
    }

    public long totImages(){
        long total = thumbList.stream().count();
        return total;
    }
}
