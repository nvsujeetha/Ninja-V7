package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC05_AddToWishList extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC05_AddToWishList.class);

    @Test(
        groups = {"regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testAddToWishList() {

        logger.debug("===== Starting Add To WishList Test =====");

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

            CategoryPage cp = new CategoryPage(getDriver());

            logger.debug("Clicking Laptops & Notebooks category");
            cp.clickLaptopsAndNotebooks();

            logger.debug("Clicking Show All products");
            cp.clickShowAll();

            Thread.sleep(500);

            logger.debug("Selecting HP product");
            cp.selectHPProduct();

            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Clicking Add to Wishlist");
            pp.addToWishlist();

            boolean status = pp.isSuccessMessageDisplayed();
            logger.debug("Wishlist success message displayed: {}", status);

            try {
                Assert.assertTrue(status, "Wishlist message not shown.");
                logger.info("Product successfully added to wishlist");

            } catch (AssertionError ae) {
                logger.error("Assertion failed: Wishlist confirmation not displayed", ae);
                throw ae;
            }

        } catch (Exception e) {
            logger.error("Exception occurred during Add To WishList test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("===== Ending Add To WishList Test =====");
    }
}
