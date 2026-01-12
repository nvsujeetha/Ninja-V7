package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.AffiliatePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC06_AddAffiliate extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC06_AddAffiliate.class);

    @Test(
        groups = {"regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testAddAffiliate() {

        logger.info("===== Starting Add Affiliate Test =====");

        try {
            HomePage hp = new HomePage(getDriver());

            logger.debug("Clicking My Account");
            hp.clickMyAccount();

            logger.debug("Navigating to Login page");
            hp.goToLogin();

            LoginPage lp = new LoginPage(getDriver());

            logger.debug("Entering login email");
            lp.setEmail("sid@cloudberry.services");

            logger.debug("Entering login password");
            lp.setPwd("Test123");

            logger.debug("Clicking Login button");
            lp.clickLogin();

            AffiliatePage ap = new AffiliatePage(getDriver());

            logger.debug("Navigating to Affiliate form");
            ap.navigateToAffiliateForm();

            logger.debug("Filling affiliate details");
            ap.fillAffiliateDetails(
                    "CloudBerry",
                    "cloudberry.services",
                    "123456",
                    "Shadab Siddiqui"
            );

            boolean status = ap.isAffiliateAdded();
            logger.debug("Affiliate added status: {}", status);

            try {
                Assert.assertTrue(status, "Affiliate details not added successfully.");
                logger.info("Affiliate details added successfully");

            } catch (AssertionError ae) {
                logger.error("Assertion failed: Affiliate was not added", ae);
                throw ae;
            }

        } catch (Exception e) {
            logger.error("Exception occurred during Add Affiliate test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("===== Ending Add Affiliate Test =====");
    }
}
