package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

public class TC001_AccountRegistrationTest extends BaseClass {
    public WebDriver driver;
    @Test
    public void verify_account_registration(){
        logger.info("****** Starting: TC001_AccountRegistrationTest ******");
        try {
            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            logger.info("clicked on My account link");
            hp.clickRegister();
            logger.info("clicked on Register link");

            AccountRegistrationPage arp = new AccountRegistrationPage(driver);

            logger.info("providing user data");
            arp.setFirstName(randomString().toUpperCase());
            arp.setLastName(randomString().toUpperCase());
            arp.setEmail(randomString() + "@gmail.com");
            arp.setTelephone("48" + randomNumber());

            String password = randomAlfaNumeric();
            arp.setPassword(password);
            arp.setConfirmPassword(password);

            arp.setPrivacyPolicy();
            arp.clickContinue();

            logger.info("validating expected message");
            String confMsg = arp.getConfirmationMsg();

            Assert.assertEquals(confMsg, "Your Account Has Been Created!");
        }catch (Exception e){
            logger.info("Test failed");
            logger.debug("Debug logs:");
            Assert.fail();
        }
        logger.info("****** TC001_AccountRegistrationTest Finished ******");
    }


}
