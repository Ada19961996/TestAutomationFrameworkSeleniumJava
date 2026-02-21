package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass {
    @Test
    public void verify_login(){
        logger.info("****** Starting: TC002_AccountLoginTest ******");
        try {
            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            logger.info("clicked on My account link");
            hp.clickLogin();
            logger.info("clicked on Login link");


            LoginPage lp = new LoginPage(driver);

            logger.info("providing user data");
            lp.enterMyEmail(p.getProperty("email"));
            lp.enterMyPassword(p.getProperty("password"));
            lp.confirmLogin();

            MyAccountPage map = new MyAccountPage(driver);
            Boolean targetPage = map.isMyAccountExist();
            Assert.assertEquals(targetPage, true, "Login failed");
//            Assert.assertTrue(targetPage);
        }catch (Exception e){
            logger.info("Test failed");
            Assert.fail();
        }

        logger.info("****** TC002_AccountRegistrationTest Finished ******");
    }

}
