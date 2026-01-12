package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.CategoryPage;
import pageObjects.ProductPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC03_AddToCart extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC03_AddToCart.class);

    @Test(
        groups = {"sanity","regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testAddToCart() {

        logger.info("===== Starting Add To Cart Test =====");

        try {
            CategoryPage cp = new CategoryPage(getDriver());

            logger.debug("Clicking on Laptops & Notebooks category");
            cp.clickLaptopsAndNotebooks();

            logger.debug("Clicking on Show All products");
            cp.clickShowAll();

            Thread.sleep(500);

            logger.debug("Selecting HP product");
            cp.selectHPProduct();

            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Setting delivery date");
            pp.setDeliveryDate();

            logger.debug("Clicking Add to Cart button");
            pp.clickAddToCart();

            boolean status = pp.isSuccessMessageDisplayed();
            logger.debug("Add to Cart success message displayed: {}", status);

            try {
                Assert.assertTrue(status, "Add to Cart Failed!");
                logger.info("Product successfully added to cart");

            } catch (AssertionError ae) {
                logger.error("Assertion failed: Product not added to cart", ae);
                throw ae; // rethrow to mark test as failed
            }

        } catch (Exception e) {
            logger.error("Exception occurred during Add To Cart test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("===== Ending Add To Cart Test =====");
    }
}
