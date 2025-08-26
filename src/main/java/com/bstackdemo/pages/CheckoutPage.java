package com.bstackdemo.pages;

import com.bstackdemo.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckoutPage extends BasePage {

    @FindBy(how = How.ID, using = "firstNameInput")
    private WebElement firstNameInput;

    @FindBy(how = How.ID, using = "lastNameInput")
    private WebElement lastNameInput;

    @FindBy(how = How.ID, using = "addressLine1Input")
    private WebElement addressInput;

    @FindBy(how = How.ID, using = "provinceInput")
    private WebElement provinceInput;

    @FindBy(how = How.ID, using = "postCodeInput")
    private WebElement postCodeInput;

    @FindBy(how = How.ID, using = "checkout-shipping-continue")
    private WebElement continueButton;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Checkout')]")
    private WebElement checkoutTitle;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Submit')]")
    private WebElement submitButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        waitForElementToBeVisible(firstNameInput);
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        waitForElementToBeVisible(lastNameInput);
        type(lastNameInput, lastName);
    }

    public void enterAddress(String address) {
        waitForElementToBeVisible(addressInput);
        type(addressInput, address);
    }

    public void enterProvince(String province) {
        waitForElementToBeVisible(provinceInput);
        type(provinceInput, province);
    }

    public void enterPostCode(String postCode) {
        waitForElementToBeVisible(postCodeInput);
        type(postCodeInput, postCode);
    }

    public void clickContinue() {
        waitForElementToBeClickable(continueButton);
        continueButton.click();
        waitForPageLoad();
    }

    public void clickSubmit() {
        waitForElementToBeClickable(submitButton);
        submitButton.click();
        waitForPageLoad();
    }

    public void fillShippingDetails(String firstName, String lastName, String address, String province, String postCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterProvince(province);
        enterPostCode(postCode);
        clickSubmit();
    }

    public String getCheckoutTitle() {
        waitForElementToBeVisible(checkoutTitle);
        return getText(checkoutTitle);
    }

    public boolean isCheckoutPageLoaded() {
        return checkoutTitle.isDisplayed() && firstNameInput.isDisplayed();
    }
}