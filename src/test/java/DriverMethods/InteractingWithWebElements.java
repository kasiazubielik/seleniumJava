package DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class InteractingWithWebElements {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://www.zooniverse.org/");
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void testZooniverse() {
        driver.findElement(By.cssSelector("button[value='sign-in']")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("hasłotestowe");
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(By.cssSelector("form")).submit();

        Assertions.assertEquals("MALAMI", driver.findElement(By.cssSelector("span[class='account-bar'] strong")).getText(),
                "Username displayed on header is not correct. The user was probably not worked in.");
    }
}
