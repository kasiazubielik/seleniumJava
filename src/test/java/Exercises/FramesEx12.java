package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FramesEx12 {

    WebDriver driver;
    WebDriverWait wait;
    By demoStoreBar = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1480, 920));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-ramek/");
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(demoStoreBar)).click();
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void mainPageButtonDisabledTest() {
        driver.switchTo().frame("main-frame");
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        Assertions.assertFalse(mainPageButton.isEnabled(), "Main page button id not disabled");
    }

    @Test
    public void imageLinkTestTest() {
        driver.switchTo().frame("main-frame")
                .switchTo().frame("image");
        WebElement mainPageLink = driver.findElement(By.xpath(".//img[@alt='Wakacje']/.."));
        Assertions.assertEquals("https://fakestore.testelka.pl/", mainPageLink.getAttribute("href"),"There is no link to main page on image");
    }

    @Test
    public void mainPageButtonEnabledTest() {
        driver.switchTo().frame("main-frame")
                .switchTo().frame("image")
                .switchTo().frame(0);
        WebElement mainPageButton = driver.findElement(By.cssSelector("a.button"));
        Assertions.assertTrue(mainPageButton.isEnabled(),"Main Page button is not enabled");
    }

    @Test
    public void logoDisplayedTest() {
        driver.switchTo().frame("main-frame");
        driver.switchTo().frame("image");
        driver.switchTo().frame(0);
        WebElement mainPageButton = driver.findElement(By.cssSelector("a.button"));
        mainPageButton.click();

        driver.switchTo().parentFrame()
                .switchTo().parentFrame();
        WebElement climbingPageButton = driver.findElement(By.cssSelector("a[name='climbing']"));
        climbingPageButton.click();

        WebElement logo = driver.findElement(By.cssSelector("img.custom-logo"));
        Assertions.assertTrue(logo.isDisplayed(), "Logo is not displayed");
    }
}
