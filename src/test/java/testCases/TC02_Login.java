package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.RetryAnalyzer;

public class TC02_Login extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC02_Login.class);

    @Test(
        groups = {"sanity","regression","datadriven"},
        dataProvider = "LoginData",
        dataProviderClass = DataProviders.class,
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testLogin(String email, String pwd) {

        logger.info("===== Starting Login Test =====");
        logger.debug("Test data received | Email: {} | Password: {}", email, pwd);

        try {
            HomePage hp = new HomePage(getDriver());
            logger.debug("Clicking on My Account");
            hp.clickMyAccount();

            logger.debug("Navigating to Login page");
            hp.goToLogin();

            LoginPage lp = new LoginPage(getDriver());
            logger.debug("Entering email");
            lp.setEmail(email);

            logger.debug("Entering password");
            lp.setPwd(pwd);

            logger.debug("Clicking Login button");
            lp.clickLogin();

            AccountPage ap = new AccountPage(getDriver());
            boolean status = ap.getMyAccountConfirmation().isDisplayed();
            logger.debug("Login status displayed: {}", status);

            try {
                Assert.assertTrue(status, "Login failed: My Account not displayed");
                logger.info("Login successful");

                logger.debug("Performing logout");
                ap.clickMyAccountDropDown();
                ap.clickLogout();
                logger.info("Logout successful");

            } catch (AssertionError ae) {
                logger.error("Assertion failed during login validation", ae);
                throw ae; // rethrow to mark test as failed
            }

        } catch (Exception e) {
            logger.error("Exception occurred during login test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("===== Ending Login Test =====");
    }
}
