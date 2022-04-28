package resources.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;

import resources.*;

public class GamePO {
    /*
    * GamePO: class
    *   Gameplay Page Object
    *   Contains all helper functions to test functionalities.
    */
    private WebDriver driver;
    private Locator locator;

    private final By OPPONENT          = By.xpath("//*[@id=\"board-layout-sidebar\"]/div/section/div/div/div[3]/div[2]/div[11]");
    private final By OPPONENT_LIST     = By.xpath("//*[@id=\"board-layout-sidebar\"]/div/section/div/div");
    private final By OPPONENT_SELECTOR = By.xpath("//button[text()=\"Choose\"]");
    private final By PLAY_BUTTON       = By.xpath("//button[text()=\"Play\"]");

    public GamePO(WebDriver driver) {
        this.driver = driver;
        locator = new Locator(driver);
    }

    public void launchGame() {
        /*
        * Selects computer opponent and launches a game.
        */

        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.chess.com/play/computer"));

        WebElement opponent = locator.locate(OPPONENT);
        WebElement list     = locator.locate(OPPONENT_LIST);
        WebElement selector = locator.locate(OPPONENT_SELECTOR);
        JavascriptExecutor js = (JavascriptExecutor) driver;  

        js.executeScript(
            "arguments[0].scrollIntoView();", list
        );
        // Choose opponent and selects it
        opponent.click();
        selector.click();
        // Locate play button and click
        WebElement playBtn = locator.locate(PLAY_BUTTON);
        playBtn.click();
    }

    public void movePiece() {
        /*
        * Moves chess piece e2 to e3
        */

        By pieceLocator  = By.xpath("//*[@id=\"board-vs-personalities\"]/div[24]");
        By targetLocator = By.xpath("//*[@id=\"board-vs-personalities\"]/div[16]");

        Actions builder = new Actions(driver);

        // Building a drag and drop action
        Action dragAndDrop = builder.clickAndHold(locator.locate(pieceLocator))
        .moveToElement(locator.locate(pieceLocator))
        .release(locator.locate(targetLocator))
        .build();
        
        // Perform
        dragAndDrop.perform();

    }
}
