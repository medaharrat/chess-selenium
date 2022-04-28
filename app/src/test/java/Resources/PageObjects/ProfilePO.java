package resources.pageobjects;

import java.util.regex.*;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import dataprovider.ConfigFileReader;
import resources.*;

public class ProfilePO {
    /*
    * ProfilePO: class
    *   Profile Page Object
    *   Contains all helper functions to test functionalities.
    */
    private WebDriver driver;
    private Locator locator;
    private ConfigFileReader configFileReader;

    private final By EDIT_PROFILE   = By.xpath("//*[@id=\"view-profile\"]/div[1]/div/div/div[2]/div/div[3]/a");
    private final By FULL_NAME      = By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[1]/div/div/div[2]/div[2]/div[1]/div[1]");
    private final By FIRST_NAME_IN  = By.xpath("//*[@id=\"profile_firstName\"]");
    private final By LAST_NAME_IN   = By.xpath("//*[@id=\"profile_lastName\"]");
    private final By COUNTRY        = By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[1]/div/div/div[2]/div[1]/div[1]");
    private final By COUNTRY_IN     = By.xpath("//*[@id=\"profile_country\"]");
    private final By PICTURE_BUTTON = By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[1]/div/div/div[1]/div/div/button");
    private final By SAVE_BUTTON    = By.xpath("//*[@id=\"profile_save\"]");
    
    public ProfilePO(WebDriver driver) {
        this.driver  = driver;
        locator = new Locator(driver);
        configFileReader = new ConfigFileReader();
    }

    public void editProfile() {
        // Locates the edit profile button and click on it.
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.chess.com/member/"+configFileReader.getUsername()));
        WebElement editButton = locator.locate(EDIT_PROFILE);
        editButton.click();
    }

    public void setProfileImage() {
        // Locates the profile picture edit button and clicks on it.
        WebElement changePicButton = locator.locate(PICTURE_BUTTON);
        changePicButton.click();
    }

    public void setCountry(String countryCode) {
        // Locates the country dropdown and selects country with countryCode.
        WebElement countrySelector = locator.locate(COUNTRY_IN);
        Select drpCountry = new Select(countrySelector);
        drpCountry.selectByValue(countryCode);
    }

    public void setFirstName(String firstname) {
        // Locates the first name input field and fills it with firstname arg.
        WebElement firstnameInput = locator.locate(FIRST_NAME_IN);
        firstnameInput.clear();
        firstnameInput.sendKeys(firstname);
    }

    public void setLastName(String lastname) {
        // Locates the last name input field and fills it with lastname arg.
        WebElement lastnameInput = locator.locate(LAST_NAME_IN);
        lastnameInput.clear();
        lastnameInput.sendKeys(lastname);
    }

    public void save() {
        // Locates the save button and click on it.
        WebElement saveButton = locator.locate(SAVE_BUTTON);
        saveButton.click();
    }

    public String[] getInfo() {
        // Locates the user's info from the profile and returns array of info.
        WebElement userName = locator.locate(FULL_NAME);
        WebElement countryFlag = locator.locate(COUNTRY);

        String userCountry = "";
        String[] countryClasses = countryFlag.getAttribute("class").split(" ");
        for (int i = 0; i < countryClasses.length; i++) {
            if (Pattern.matches("country-[\\d]+\\s*", countryClasses[i])) {
                userCountry = countryClasses[i];
                break;
            }
        }

        return new String[] {
            userName.getText(), 
            userCountry.split("-")[1].trim(),
        };
    }
}
