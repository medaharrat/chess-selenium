package resources;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import dataprovider.ConfigFileReader;
import resources.pageobjects.*;

public class Chess {
    /*
    * Chess: class
    *   Contains all helper functions to create tests.
    */
    private WebDriver driver;

    private AuthPO auth;
    private HomePO home;
    private ProfilePO profile;
    private GamePO game;

    private ConfigFileReader configFileReader;
    private String URL;

    public Chess(WebDriver driver) {
        this.driver = driver;

        auth    = new AuthPO(driver);
        home    = new HomePO(driver);
        profile = new ProfilePO(driver);
        game    = new GamePO(driver);

        configFileReader = new ConfigFileReader();
        URL = configFileReader.getURL();
    }

    public void loginWithValidCredentials() {
        /* [Documentation]
        * User should be able to log in
        */
        home.openPage(this.URL);
        auth.startLogin();

        String username = configFileReader.getUsername();
        String password = configFileReader.getPassword();

        auth.fillUsername(username);
        auth.fillPassword(password);
        auth.clickSubmit();
        
        Assert.assertTrue(
            auth.isLoggedIn()
        );
    }

    public void loginWithInvalidCredentials() {
        /* [Documentation]
        * User should not be able to login with invalid credentials
        */
        home.openPage(URL);
        auth.startLogin();

        auth.fillUsername("invalid");
        auth.fillPassword("invalid");
        auth.clickSubmit();

        Assert.assertTrue(
            auth.loginErrorDisplayed()
        );
    }

    public void logoutFromAccount() {
        /* [Documentation]
        * User should be able to logout from his account
        */
        auth.logout();

        Assert.assertTrue(
            auth.isLoggedOut()
        );
    }

    public void editProfileData() {
        /* [Documentation]
        * User should be able to edit his profile information
        */
        home.openProfile();
        profile.editProfile();

        String firstname   = configFileReader.getFirstName();
        String lastname    = configFileReader.getLastName();
        String countryCode = configFileReader.getCountryCode();

        profile.setFirstName(firstname);
        profile.setLastName(lastname);
        profile.setCountry(countryCode);

        profile.save();

        Assert.assertTrue(
            profile.getInfo()[0].equals(firstname + " " + lastname)
            &&
            profile.getInfo()[1].equals(countryCode)
        );
    }

    public void playAgainstComputer() {
        /* [Documentation]
        * User should be able to play a game against a computer
        */
        home.openGamePage();
        game.launchGame();
        game.movePiece();
    }
}
