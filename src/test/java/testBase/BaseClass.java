package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;
    @BeforeClass(groups = {"sanity", "master", "regression","DDT"})
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
    @AfterClass(groups = {"sanity", "master", "regression", "DDT"})
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
    public String captureScreen(String tname) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + timeStamp +".png";

        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }

}
