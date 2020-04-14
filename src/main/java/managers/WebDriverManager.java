package managers;

import dataproviders.ConfigurationFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.function.Supplier;

public class WebDriverManager {

    public WebDriver driver;
    public ConfigurationFileReader configFileReader;

    private final Supplier<WebDriver> chromeSupplier = () -> {
        try {
            configFileReader = new ConfigurationFileReader();
            System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
        } catch (Exception ex) {
            ex.getMessage();
        }
        return new ChromeDriver();
    };

    public WebDriver getDriver() {
        driver = chromeSupplier.get();
        return driver;
    }

    public void closeDriver(){
        driver.quit();
    }
}
