package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebStorageByJavaScriptEx19 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product/fuerteventura-sotavento/");
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void localStorageKeysTest() {
        long size = (long) ((JavascriptExecutor) driver).executeScript("return localStorage.length");
        Assertions.assertEquals(1, size, "Size of localStorage is not what expected");
    }

    @Test
    public void sessionStorageKeysTest() {
        long size = (long) ((JavascriptExecutor) driver).executeScript("return sessionStorage.length");
        Assertions.assertEquals(2, size, "Size of sessionStorage is not what expected");
    }

    @Test
    public void addingProductNewKeyTest() {
        WebElement addToCart = driver.findElement(By.cssSelector("button[name='add-to-cart']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", addToCart);
        addToCart.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until((WebDriver d)->(long) ((JavascriptExecutor) d).executeScript("return sessionStorage.length") == 3);

        Set<String> keys = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            String key = (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.key(arguments[0])", i);
            keys.add(key);
        }

        Assertions.assertTrue(keys.contains("wc_cart_created"), "Expected key is missing");
    }

    @Test
    public void removingKeyTest() {
        String wcFragmentsKey = null;

        for (int i = 0; i < 2; i++) {
            String key = (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.key(arguments[0])", i);
            if (key.contains("wc_fragments")) {
                wcFragmentsKey = key;
            }
        }
        ((JavascriptExecutor) driver).executeScript("sessionStorage.removeItem(arguments[0]);", wcFragmentsKey);
        long size = (long) ((JavascriptExecutor) driver).executeScript("return sessionStorage.length");
        Assertions.assertEquals(1, size, wcFragmentsKey + " key was not removed.");
    }
}
