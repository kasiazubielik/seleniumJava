package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SelectElementEx16 {

    WebDriver driver;
    WebDriverWait wait;
    By demoStoreNoticeDismiss = By.cssSelector("a[class*='dismiss-link']");
    By windsurfingGroup = By.cssSelector("a[href*='windsurfing']");

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        driver.navigate().to("https://fakestore.testelka.pl/");
        driver.findElement(demoStoreNoticeDismiss).click();
        driver.findElement(windsurfingGroup).click();
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void priceOrderAscTest() {
        WebElement orderBy = driver.findElements(By.cssSelector("select.orderby")).get(0);
        Select orderByDropdown = new Select(orderBy);
        orderByDropdown.selectByValue("price");
        WebElement firstPriceElement = driver.findElements(By.cssSelector("span.price")).get(0);
        Assertions.assertEquals("2 900,00 zł", firstPriceElement.getText(), "First price is not the lowest price.");
    }

    @Test
    public void priceOrderDescTest() {
        WebElement dropdownElement = driver.findElement(By.cssSelector("select.orderby"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue("price-desc");
        WebElement firstPriceElement = driver.findElements(By.cssSelector("span.price")).get(0);
        Assertions.assertEquals("5 399,00 zł", firstPriceElement.getText(), "First price is not the highest price.");
    }
}
