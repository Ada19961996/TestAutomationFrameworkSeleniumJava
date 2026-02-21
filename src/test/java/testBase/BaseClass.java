package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;
    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String browser){
//        loading config.properties
        try {
            FileReader file = new FileReader("./src//test//resources//config.properties");
            p = new Properties();
            p.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger = LogManager.getLogger(this.getClass());

        switch (browser.toLowerCase()){
            case "chrome": driver= new ChromeDriver(); break;
            case "edge": driver= new EdgeDriver(); break;
            case "firefox": driver= new FirefoxDriver(); break;
            default: System.out.println("Invalid browser name..."); return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL")); //read url from config file
        driver.manage().window().maximize();

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        String generatedRandomString = RandomStringUtils.randomAlphabetic(5);
        return generatedRandomString;
    }

    public String randomNumber(){
        String generatedRandomNumber = RandomStringUtils.randomNumeric(9);
        return generatedRandomNumber;
    }
    public String randomAlfaNumeric(){
        String generatedRandomString = RandomStringUtils.randomAlphabetic(4);
        String generatedRandomNumber = RandomStringUtils.randomNumeric(4);
        String generatedRandomAlfaNumeric = generatedRandomString + generatedRandomNumber;
        return generatedRandomAlfaNumeric;
    }

}
