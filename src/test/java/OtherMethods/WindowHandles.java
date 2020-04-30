package OtherMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowHandles {

    WebDriver driver;
    WebDriverWait wait;
    By cookieAccept = By.cssSelector("a#cn-close-notice");

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        driver.navigate().to("https://testelka.pl/blog/");
        driver.findElement(cookieAccept).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieAccept));
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void windowHandleTest() {
        driver.findElement(By.cssSelector("span.dashicons-video-alt3")).click();
        Set<String> window = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();
        window.remove(parentWindow);
        String secondWindow = window.iterator().next();
        driver.switchTo().window(secondWindow);
        String activeWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector("div#logo-icon-container")).click();
        driver.switchTo().window(parentWindow);

    }

}
