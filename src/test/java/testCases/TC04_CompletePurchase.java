package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.CategoryPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC04_CompletePurchase extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC04_CompletePurchase.class);

    @Test(
        groups = {"sanity","regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testCompletePurchase() {

        logger.info("===== Starting Complete Purchase Test =====");

        try {
            CategoryPage cp = new CategoryPage(getDriver());

            logger.debug("Clicking on Laptops & Notebooks category");
            cp.clickLaptopsAndNotebooks();

            logger.debug("Clicking Show All products");
            cp.clickShowAll();

            Thread.sleep(500);

            logger.debug("Selecting HP product");
            cp.selectHPProduct();

            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Setting delivery date");
            pp.setDeliveryDate();

            logger.debug("Clicking Add to Cart");
            pp.clickAddToCart();

            logger.debug("Proceeding to Checkout");
            pp.clickCheckout();

            CheckoutPage cop = new CheckoutPage(getDriver());

            logger.debug("Clicking Login on Checkout page");
            cop.clickLogin();

            LoginPage lp = new LoginPage(getDriver());

            logger.debug("Entering login credentials");
            lp.setEmail("nsujeetha@gmail.com");
            lp.setPwd("Happylearning");
            lp.clickLogin();

            logger.debug("Completing checkout process");
            cop.completeCheckout();

            ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());
            boolean orderStatus = confirmationPage.isOrderPlaced();
            logger.debug("Order placed status: {}", orderStatus);

            try {
                Assert.assertTrue(orderStatus, "Order placement failed!");
                logger.info("Order successfully placed");

            } catch (AssertionError ae) {
                logger.error("Assertion failed: Order was not placed successfully", ae);
                throw ae;
            }

        } catch (Exception e) {
            logger.error("Exception occurred during Complete Purchase test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("===== Ending Complete Purchase Test =====");
    }
}
