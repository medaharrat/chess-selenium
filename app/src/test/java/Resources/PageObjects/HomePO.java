package resources.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import resources.*;

public class HomePO {
    /*
    * HomePO: class
    *   Landing Page Object
    *   Contains all helper functions to test functionalities.
    */
    private WebDriver driver;
    private Locator locator;

    private final By BODY_LOCATOR = By.tagName("body");
    private final By PROFILE      = By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[1]/div/div/div/div[2]/a");
    private final By GAMEPAGE     = By.xpath("//*[@id=\"quick-link-computer\"]");
    private final By MODAL_ACTION = By.xpath("/html/body/div[27]/div[2]/div/div/div[2]/div[2]/button");
    private final By MODAL_CANCEL = By.xpath("//*[@id=\"vue-instance\"]/div[3]/div[2]/div/div/div[3]/button");
    
    public HomePO(WebDriver driver) {
        this.driver = driver;
        locator = new Locator(driver);
    }

    public void openPage(String url) {
        /*
        * Takes a URL as a String object
        * Opens a page and asserts its open.
        */
        driver.get(url);
        WebElement body = locator.locate(BODY_LOCATOR);

        // Handle modal/popup
        String modelxPath = "//*[@id=\"vue-instance\"]/div[3]/div[2]";
        if(locator.exists(By.xpath(modelxPath))) {
            WebElement cancel = locator.locate(MODAL_CANCEL);
            cancel.click();
        }

        Assert.assertTrue(
            body.getText().contains("Play Chess")
        );
    }

    public void openProfile() {
        /*
        * Locates the profile button
        * Opens the profile and asserts its open.
        */
        WebElement profile = locator.locate(PROFILE);
        profile.click();
        // Add assertion
    }

    public void openGamePage() {
        /*
        * Locates the play button
        * Opens the Play page and asserts its open.
        */

        WebElement gamepage = locator.locate(GAMEPAGE);
        gamepage.click();

        // handle modal/popup
        String modelxPath = "/html/body/div[27]/div[2]/div/div";
        if (locator.exists(By.xpath(modelxPath))) {
            WebElement modalAction = locator.locate(MODAL_ACTION);
            modalAction.click();
        }

        // Add assertion
    }

    public void closePage() {
        /*
        * Closes the home page
        * by quitting the driver.
        */
        driver.close();
    }
}
