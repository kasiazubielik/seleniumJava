package OtherMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class WebStorageByJavaScript {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void sessionLocalExample() {
        driver.navigate().to("https://airly.eu/map/pl/#54.34854,18.64966,i3432");
        String key = "persist:map";

        String value = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.getItem(arguments[0]);", key);
        ((JavascriptExecutor) driver).executeScript("localStorage.setItem(arguments[0], arguments[1]);", "spell", "Alohomora!");
        ((JavascriptExecutor) driver).executeScript("localStorage.removeItem(arguments[0]);", key);
        String indexValue = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.key(arguments[0]);", 2);
        long size = (long) ((JavascriptExecutor) driver).executeScript("return localStorage.length;");
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
    }

    @Test
    public void sessionStorageExample() {
        driver.navigate().to("https://www.youtube.com/watch?v=EAog1flzb5k");
        String key = "yt-remote-session-name";
        String value = (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.getItem(arguments[0]);", key);
        ((JavascriptExecutor) driver).executeScript("sessionStorage.setItem(arguments[0], arguments[1]);", "spell", "Alohomora!");
        ((JavascriptExecutor) driver).executeScript("sessionStorage.removeItem(arguments[0]);", key);
        String indexValue = (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.key(arguments[0]);", 2);
        long size = (long) ((JavascriptExecutor) driver).executeScript("return sessionStorage.length;");
        ((JavascriptExecutor) driver).executeScript("sessionStorage.clear();");
    }
}
