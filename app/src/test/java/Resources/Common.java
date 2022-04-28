package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

import dataprovider.ConfigFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Common {
    /*
    * Common: class
    *   Contains common functions.
    */
    private WebDriver driver;
    private WebDriverWait wait;
    private ConfigFileReader configFileReader;
    private long implicitWait;

    public Common() {
        configFileReader  = new ConfigFileReader();
        this.implicitWait = configFileReader.getImplicitWait();
    }

    public void Setup() {
        /*
        * Sets up the connection to the driver
        * Instanciates the WebDriverWait instance.
        */
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, this.implicitWait);
    }

    public void TearDown() {
        /*
        * Close the connection to the driver.
        */
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        /*
        * Returns the instance's driver.
        */
        return this.driver;
    }
}