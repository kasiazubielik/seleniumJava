package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FindingObjectsEx5 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findingElementsTest() {
        driver.findElement(By.id("woocommerce-product-search-field-0"));

        driver.findElement(By.id("username"));
        driver.findElement(By.name("username"));

        driver.findElement(By.id("password"));
        driver.findElement(By.name("password"));

        driver.findElement(By.name("login"));

        driver.findElement(By.id("rememberme"));
        driver.findElement(By.name("rememberme"));

        driver.findElement(By.linkText("Nie pamiętasz hasła?"));

        driver.findElement(By.linkText("Żeglarstwo"));
    }
}
