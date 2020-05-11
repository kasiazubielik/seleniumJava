package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebStorageEx19 {

    ChromeDriver driver;

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
        Assertions.assertEquals(1, driver.getLocalStorage().size(), "Number of keys in Local Storage is not what expected");
    }

    @Test
    public void sessionStorageKeysTest() {
        Assertions.assertEquals(2, driver.getSessionStorage().size(), "Number of keys in Session Storage is not what expected");
    }

    @Test
    public void addingProductTest() {
        WebElement addToCart = driver.findElement(By.cssSelector("button[name='add-to-cart']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", addToCart);
        addToCart.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(d->driver.getSessionStorage().size() == 3);
        Assertions.assertTrue(driver.getSessionStorage().keySet().contains("wc_cart_created"), "Expected key is missing");

    }

    @Test
    public void removingKeyTest() {
        String wcFragmentsKey = null;
        Set<String> keys = driver.getSessionStorage().keySet();
        for (String key : keys) {
            if (key.contains("wc_fragments")) {
                wcFragmentsKey = key;}
        }
        String removedKey = driver.getSessionStorage().removeItem(wcFragmentsKey);

        Assertions.assertTrue(removedKey != null, wcFragmentsKey + " key was not removed.");
    }
}
