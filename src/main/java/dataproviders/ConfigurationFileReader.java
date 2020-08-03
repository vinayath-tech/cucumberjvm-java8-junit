package dataproviders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Supplier;

public class ConfigurationFileReader {

    private Properties properties;
    private final String propertyFilePath = "config//Configuration.properties";

    public ConfigurationFileReader() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(propertyFilePath));
        properties = new Properties();
        properties.load(reader);
    }

    private Supplier<String> driverPath = () -> {
       String driverNav = properties.getProperty("driverPath");
       return driverNav;
    };

    private Supplier<String> ffDriverPath = () -> {
        String ffDriverNav = properties.getProperty("ffDriverPath");
        return ffDriverNav;
    };

    private Supplier<String> applicationUrl = () -> {
        String baseUrl = properties.getProperty("baseUrl");
        return baseUrl;
    };

    public String getApplicationUrl() {
        return applicationUrl.get();
    }

    public String getDriverPath() {
        return driverPath.get();
    }

    public String getFFDriverPath() {
        return ffDriverPath.get();
    }
}
