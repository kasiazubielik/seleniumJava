package DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Frames {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://www.nasa.gov/");

        wait = new WebDriverWait(driver, 15);
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void frameByIdExample() {
        driver.switchTo().frame("twitter-widget-0");
        WebElement viewOnTwitter = driver.findElement(By.cssSelector("a[data-scribe*='twitter_url']"));
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("div.navbar-header>a.logo"));
    }

    @Test
    public void frameByIndexExample() {
        driver.switchTo().frame(0);
        WebElement viewOnTwitter = driver.findElement(By.cssSelector("a[data-scribe*='twitter_url']"));
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("div.navbar-header>a.logo"));
    }

    @Test
    public void frameByWebElementExample() {
        WebElement frame = driver.findElement(By.cssSelector("iframe#twitter-widget-0"));
        driver.switchTo().frame(frame);
        WebElement viewOnTwitter = driver.findElement(By.cssSelector("a[data-scribe*='twitter_url']"));
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("div.navbar-header>a.logo"));
    }
 }
