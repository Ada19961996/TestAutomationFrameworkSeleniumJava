package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass
{

    @Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
    public void verify_loginDDT(String email, String password, String exp)
    {
        logger.info("**** Starting TC003_LoginDDT *****");

        try {

            //Home page
            HomePage hp=new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin(); //Login link under MyAccount

            //Login page
            LoginPage lp=new LoginPage(driver);
            lp.enterMyEmail(email);
            lp.enterMyPassword(password);
            lp.confirmLogin(); //Login button

            //My Account Page
            MyAccountPage map=new MyAccountPage(driver);
            Boolean targetPage = map.isMyAccountExist();

            if(exp.equalsIgnoreCase("Valid"))
            {
                if(targetPage==true)
                {
                    map.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp.equalsIgnoreCase("Invalid"))
            {
                if(targetPage==true)
                {
                    map.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }
        }
        catch(Exception e)
        {
            Assert.fail("An exception occurred: " + e.getMessage());
        }

        logger.info("**** Finished TC003_LoginDDT *****");
    }
}









