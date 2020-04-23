package DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waits {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://old.dunckelfeld.de/");

        wait = new WebDriverWait(driver, 15);
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void waitExample() {
        WebElement animation = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".startanimation")));
        wait.until(ExpectedConditions.stalenessOf(animation));
        driver.findElement(By.cssSelector("a.cc-dismiss")).click();
        driver.findElement(By.cssSelector("a[class*='button']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='UNIVERSAL MUSIC Deutschland']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='#zusammenfassung']"))).click();
        By wireframe = (By.xpath(".//span[text()='Wireframes']/../span[@class='countup']"));
        wait.until(ExpectedConditions.textToBe(wireframe, "670"));
    }
}
