package com.amazon.automation.commontests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    // Initialize driver as per the driver mentioned in the Global data file
    public void initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream dataStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/amazon/resources/GlobalData.properties");
        prop.load(dataStream);
        String browserName = prop.getProperty("browser");
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("firefox")){
            // setup firefox
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Window maximize
        driver.manage().window().maximize();
    }

    // Launch the application before each Test method
    @BeforeMethod
    public void launchApp() throws IOException {
        initializeDriver();
        Properties prop = new Properties();
        FileInputStream dataStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/amazon/resources/GlobalData.properties");
        prop.load(dataStream);
        String url = prop.getProperty("url");
        driver.get(url);
    }

    // Close all browser windows after each Test method
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
