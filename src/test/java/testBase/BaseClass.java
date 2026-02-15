package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    @BeforeClass
    public void setup(){
        logger = LogManager.getLogger(this.getClass());

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo");
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
