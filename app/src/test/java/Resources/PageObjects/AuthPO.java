package resources.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import resources.*;

public class AuthPO {
    /*
    * AuthPO: class
    *   Authentication Page Object
    *   Contains all helper functions to test functionalities.
    */
    private WebDriver driver;
    private Locator locator;

    private final By LOGIN_BUTTON    = By.xpath("//*[@id=\"sb\"]/div[3]/a[9]");
    private final By LOGIN_USERNAME  = By.xpath("//*[@id=\"username\"]");
    private final By LOGIN_PASSWORD  = By.xpath("//*[@id=\"password\"]");
    private final By LOGIN_SUBMIT    = By.xpath("//*[@id=\"login\"]");
    private final By NEW_GAME_LINK   = By.xpath("//*[@id=\"quick-link-new_game\"]");
    private final By SETTINGS_BUTTON = By.xpath("//*[@id=\"sb\"]/div[4]/a/span[2]");
    private final By LOGOUT_BUTTON   = By.xpath("//*[@id=\"sb\"]/div[4]/div[1]/button");
    private final By LOGIN_ERROR     = By.xpath("/html/body/div[1]/div/main/div/p");

    public AuthPO(WebDriver driver) {
        this.driver  = driver;
        locator = new Locator(driver);
    }
    
    public void startLogin() {
        // Locates the login button and clicks it.
        WebElement loginBtn = locator.locate(LOGIN_BUTTON);
        loginBtn.click();
    }

    public void fillUsername(String username) {
        // Locates the password input field and fills it with username arg.
        WebElement usernameInput = locator.locate(LOGIN_USERNAME);
        usernameInput.sendKeys(username);
    }

    public void fillPassword(String password) {
        // Locates the password input field and fills it with password arg.
        WebElement passwordInput = locator.locate(LOGIN_PASSWORD);
        passwordInput.sendKeys(password);
    }

    public void clickSubmit() {
        // Locates the submit button and clicks it.
        WebElement submitBtn = locator.locate(LOGIN_SUBMIT);
        submitBtn.click();
    }

    public Boolean loginErrorDisplayed() {
        // Locates the error message and validates it.
        WebElement error = locator.locate(LOGIN_ERROR);
        String errorMessage = error.getText();
        return errorMessage.toLowerCase().contains("invalid");
    }

    public void logout() {
        /*
        * Locates settings button, hovers over it 
        * and clicks on Logout
        */
        Actions actions = new Actions(driver);
        WebElement settings = locator.locate(SETTINGS_BUTTON);
        actions.moveToElement(settings).perform();
        locator.locate(LOGOUT_BUTTON).click();
    }

    public Boolean isLoggedIn() {
        /*
        * Checks if the home page contains
        * "New Game" message, which is only the case
        * if the user is logged in.
        */
        WebElement newGameMessage = locator.locate(NEW_GAME_LINK);
        String output = newGameMessage.getText();

        return (Boolean) output.equals("New Game");
    }
    
    public Boolean isLoggedOut() {
        /*
        * Checks if the auth button contains
        * "Log In" which is the case if the user
        * is not logged in.
        */
        WebElement loginBtn = locator.locate(LOGIN_BUTTON);
        String output = loginBtn.getText();

        return (Boolean) output.equals("Log In");
    }
}
