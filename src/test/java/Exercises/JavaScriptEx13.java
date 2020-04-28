package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JavaScriptEx13 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product/yoga-i-pilates-w-portugalii/");
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void headerCartTest() {
        WebElement description = driver.findElement(By.cssSelector("div#tab-description"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", description);
        List<WebElement> headerCart = driver.findElements(By.cssSelector("section.storefront-sticky-add-to-cart--slideInDown"));
        Assertions.assertTrue(headerCart.size() == 1, "Header cart is not displayed after scrolling to description");
    }

    @Test
    public void headerCartTest2() {
        WebElement description = driver.findElement(By.cssSelector("div#tab-description"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", description);
        WebElement headerCart = driver.findElement(By.cssSelector("section[class='storefront-sticky-add-to-cart storefront-sticky-add-to-cart--slideInDown']"));
        Assertions.assertTrue(headerCart.isDisplayed(), "Header cart is not displayed after scrolling to description");
    }
 }
