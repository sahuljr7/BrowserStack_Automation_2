package com.bstackdemo.pages;

import com.bstackdemo.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'username')]//input")
    private WebElement usernameInput;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'password')]//input")
    private WebElement passwordInput;

    @FindBy(how = How.ID, using = "login-btn")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//span[@class='username']")
    private WebElement usernameLabel;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        waitForElementToBeVisible(usernameInput);
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForElementToBeVisible(passwordInput);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        waitForPageLoad();
    }

    public String getLoggedInUsername() {
        waitForElementToBeVisible(usernameLabel);
        return getText(usernameLabel);
    }

    public boolean isLoginPageLoaded() {
        return loginButton.isDisplayed() && usernameInput.isDisplayed();
    }
}