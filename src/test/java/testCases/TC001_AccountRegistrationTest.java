package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import java.time.Duration;

public class TC001_AccountRegistrationTest {
    public WebDriver driver;

    @BeforeClass
    public void setup(){
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
    @Test
    public void verify_account_registration(){
        HomePage hp = new HomePage(driver);

        hp.clickMyAccount();
        hp.clickRegister();

        AccountRegistrationPage arp = new AccountRegistrationPage(driver);

        arp.setFirstName("ABCD");
        arp.setLastName("EFGHIJK");
        arp.setEmail("abcd.efghijk@gmail.com");
        arp.setTelephone("48666748982");

        arp.setPassword("password");
        arp.setConfirmPassword("password");

        arp.setPrivacyPolicy();
        arp.clickContinue();
        String confMsg = arp.getConfirmationMsg();

        Assert.assertEquals(confMsg, "Your Account Has Been Created!");



    }
}
