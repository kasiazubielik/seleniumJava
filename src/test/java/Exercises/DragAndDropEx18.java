package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class DragAndDropEx18 {

    WebDriver driver;
    Actions actions;
    WebElement draggable;
    WebElement droppable;
    String expectedMessage;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/actions/");

        draggable = driver.findElement(By.cssSelector("#draggable"));
        droppable = driver.findElement(By.cssSelector("#droppable"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", draggable);
        expectedMessage = "Dropped!";

        actions = new Actions(driver);
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void dropToElementTest() {
        actions.dragAndDrop(draggable, droppable).build().perform();
        Assertions.assertEquals(expectedMessage, droppable.getText(), "Element is not dropped.");
    }

    @Test
    public void dropToButtomRightCornerTest() {
        actions.clickAndHold(draggable).moveToElement(droppable, 74, 74).release().build().perform();
        Assertions.assertEquals(expectedMessage, droppable.getText(), "Message in the element was not changed. Element is not dropped.");
    }

    @Test
    public void moveByOffsetTest() {
//        actions.dragAndDropBy(draggable, 160, 40).build().perform();
        actions.clickAndHold(draggable).moveByOffset(160, 40).release().build().perform();
        Assertions.assertEquals(expectedMessage, droppable.getText(), "Element is not dropped.");
    }

}
