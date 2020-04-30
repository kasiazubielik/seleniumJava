package OtherMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JavaScripts {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");
        driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.MILLISECONDS);
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void exampleTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('I have written something in console');");
        String domainName = (String) js.executeScript("return document.domain;");
    }

    @Test
    public void asyncTest() {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor)driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Elapsed time: " + elapsedTime);
    }

    @Test
    public void syncTest() {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor)driver).executeScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Elapsed time: " + elapsedTime);
    }
}
