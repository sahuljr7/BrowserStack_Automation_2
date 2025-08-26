package com.bstackdemo.pages;

import com.bstackdemo.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(how = How.CLASS_NAME, using = "shelf-item")
    private List<WebElement> productItems;

    @FindBy(how = How.CLASS_NAME, using = "shelf-item__buy-btn")
    private List<WebElement> addToCartButtons;

    @FindBy(how = How.CLASS_NAME, using = "buy-btn")
    private WebElement buyNowButton;

    @FindBy(how = How.CLASS_NAME, using = "float-cart__close-btn")
    private WebElement closeCartButton;

    @FindBy(how = How.CLASS_NAME, using = "bag__quantity")
    private WebElement cartQuantity;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'username')]")
    private WebElement loggedInUsername;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public int getProductCount() {
        return productItems.size();
    }

    public void addProductToCart(int index) {
        if (index >= 0 && index < addToCartButtons.size()) {
            waitForElementToBeClickable(addToCartButtons.get(index));
            addToCartButtons.get(index).click();
            // Wait for cart to update
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void clickBuyNow() {
        waitForElementToBeClickable(buyNowButton);
        buyNowButton.click();
        waitForPageLoad();
    }

    public void closeCart() {
        try {
            waitForElementToBeClickable(closeCartButton);
            closeCartButton.click();
        } catch (Exception e) {
            // Cart might not be open
        }
    }

    public int getCartQuantity() {
        try {
            waitForElementToBeVisible(cartQuantity);
            return Integer.parseInt(getText(cartQuantity));
        } catch (Exception e) {
            return 0;
        }
    }

    public String getLoggedInUsername() {
        waitForElementToBeVisible(loggedInUsername);
        return getText(loggedInUsername);
    }

    public boolean isHomePageLoaded() {
        return productItems.size() > 0 && addToCartButtons.size() > 0;
    }
}