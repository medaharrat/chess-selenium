import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import resources.*;

public class MainTests {
    /*
    * MainTests: class
    *   Contains all tests.
    */
    private WebDriver driver;
    private Chess chessdotcom;
    
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // Avoid cloudflare check
        options.setExperimentalOption("useAutomationExtension", false); 
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        chessdotcom = new Chess(driver);
    }
    
    @Test
    public void testValidAuthLogin() {
        chessdotcom.loginWithValidCredentials();
    }

    @Test
    public void testInvalidAuthLogin() {
        chessdotcom.loginWithInvalidCredentials();
    }

    @Test
    public void testAuthLogout() {
        chessdotcom.loginWithValidCredentials();
        chessdotcom.logoutFromAccount(); 
    }
    
    @Test
    public void testEditProfile() {
        chessdotcom.loginWithValidCredentials();
        chessdotcom.editProfileData();
    }
    
    @Test
    public void testGameAgainstComputer() {
        chessdotcom.loginWithValidCredentials();
        chessdotcom.playAgainstComputer();
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
