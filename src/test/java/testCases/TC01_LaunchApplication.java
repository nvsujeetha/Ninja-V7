package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC01_LaunchApplication extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC01_LaunchApplication.class);

    @Test(
        groups = {"sanity", "regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testLaunchApplication() {

        logger.info("===== TC01_LaunchApplication test started =====");

        try {
            logger.debug("Initializing HomePage object");
            HomePage hp = new HomePage(getDriver());

            logger.debug("Fetching page title");
            String actualTitle = getDriver().getTitle();
            logger.debug("Actual page title: {}", actualTitle);

            logger.debug("Asserting page title");
            Assert.assertEquals(actualTitle, "Your store of fun", "Page title mismatch!");

            logger.info("TC01_LaunchApplication test PASSED");

        } catch (AssertionError ae) {
            logger.error("Assertion failed in TC01_LaunchApplication", ae);
            throw ae; // IMPORTANT: rethrow to trigger retry

        } catch (Exception e) {
            logger.error("Unexpected exception occurred in TC01_LaunchApplication", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("===== TC01_LaunchApplication test completed =====");
    }
}
