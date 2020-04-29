package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AlertsEx14 {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        js = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver, 5);

        driver.navigate().to("https://jsfiddle.net/nm134se7/");
        driver.switchTo().frame("result");
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void acceptConfirmWindowTest() {
        driver.findElement(By.cssSelector("[onclick='confirmFunction()']")).click();
        driver.switchTo().alert().accept();
        String message = driver.findElement(By.cssSelector("#demo")).getText();
        Assertions.assertEquals("Wybrana opcja to OK!", message, "Message is not what expected");
    }

    @Test
    public void dissmisConfirmWindowTest() {
        driver.findElement(By.cssSelector("[onclick='confirmFunction()']")).click();
        driver.switchTo().alert().dismiss();
        String message = driver.findElement(By.cssSelector("#demo")).getText();
        Assertions.assertEquals("Wybrana opcja to Cancel!", message, "Message is not what expected");
    }

    @Test
    public void acceptPromptWindowTest() {
        driver.findElement(By.cssSelector("[onclick='promptFunction()']")).click();
        String name = "Harry Potter";
        driver.switchTo().alert().sendKeys(name);
        driver.switchTo().alert().accept();
        String message = driver.findElement(By.cssSelector("#prompt-demo")).getText();
        Assertions.assertEquals("Cześć " + name + "! Jak leci?", message, "TeMessage is not what expected");

    }

    @Test
    public void dismissPromptWindowTest() {
        driver.findElement(By.cssSelector("[onclick='promptFunction()']")).click();
        driver.switchTo().alert().dismiss();
        String message = driver.findElement(By.cssSelector("#prompt-demo")).getText();
        Assertions.assertEquals("Użytkownik anulował akcję.", message, "Message is not what expected");

    }


}
