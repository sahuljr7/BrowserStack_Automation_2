package com.bstackdemo.tests;

import com.bstackdemo.pages.CheckoutPage;
import com.bstackdemo.pages.HomePage;
import com.bstackdemo.pages.LoginPage;
import com.bstackdemo.testbase.TestBase;
import com.bstackdemo.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class E2ETest extends TestBase {

    @BeforeMethod
    public void navigateToBase() {
        driver.get(ConfigReader.getBaseUrl());
    }

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        
        // Navigate to login page
        driver.get(ConfigReader.getLoginUrl());
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should be loaded");
        
        // Perform login
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        
        // Verify login success - should be redirected to home page
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded after login");
        Assert.assertTrue(driver.getCurrentUrl().contains("bstackdemo.com"), "Should be on home page");
        Assert.assertEquals(homePage.getLoggedInUsername(), "demouser", "Username should match");
    }

    @Test(priority = 2)
    public void testAddProductToCart() {
        HomePage homePage = new HomePage(driver);
        
        // Verify we're on home page
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        Assert.assertTrue(homePage.getProductCount() > 0, "Should have products displayed");
        
        // Add product to cart
        int initialCartQuantity = homePage.getCartQuantity();
        homePage.addProductToCart(0);
        
        // Verify product was added to cart
        Assert.assertEquals(homePage.getCartQuantity(), initialCartQuantity + 1, "Cart quantity should increase by 1");
    }

    @Test(priority = 3)
    public void testCheckoutProcess() {
        HomePage homePage = new HomePage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        
        // Add product first
        homePage.addProductToCart(0);
        Assert.assertTrue(homePage.getCartQuantity() > 0, "Should have items in cart");
        
        // Click buy now to proceed to checkout
        homePage.clickBuyNow();
        
        // Verify we're on checkout page
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page should be loaded");
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Should be on checkout page");
        Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout", "Checkout title should match");
        
        // Fill shipping details
        checkoutPage.fillShippingDetails("John", "Doe", "123 Main St", "California", "90001");
        
        // Verify we moved to next step (optional - depends on site flow)
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Should still be in checkout process");
    }

    @Test(priority = 4)
    public void testEndToEndFlow() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        
        // Login
        driver.get(ConfigReader.getLoginUrl());
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        Assert.assertTrue(homePage.isHomePageLoaded(), "Should be on home page after login");
        
        // Add product to cart
        homePage.addProductToCart(1);
        Assert.assertEquals(homePage.getCartQuantity(), 1, "Should have 1 item in cart");
        
        // Proceed to checkout
        homePage.clickBuyNow();
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page should be loaded");
        
        // Fill shipping details
        checkoutPage.fillShippingDetails("Jane", "Smith", "456 Oak Ave", "New York", "10001");
        
        // Basic verification that we progressed in checkout
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Should be in checkout process");
    }
}