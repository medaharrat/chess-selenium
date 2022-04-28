package resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Locator {
    /*
    * Locator: class
    *   Contains common functions used to locate web elements.
    */
    private WebDriver driver;
    private WebDriverWait wait;

    public Locator(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public WebElement locate(By locator) {
        /*
        * Takes a locator as an argument
        * Returns the element.
        */
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public Boolean exists(By locator) {
        /*
        * Takes a locator as an argument
        * Returns true if the page contains this element.
        */
        Boolean exists = false;
	    if (driver.findElements(locator).size() != 0) {
            exists = true;
        } 
        return exists;
    }
}
