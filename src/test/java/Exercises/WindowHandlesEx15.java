package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowHandlesEx15 {

    WebDriver driver;
    WebDriverWait wait;
    By demoStoreNoticeDismiss = By.cssSelector("a[class*='dismiss-link']");
    By pilateGroup = By.cssSelector("a[href*='pilates']");
    By product = By.cssSelector("li.post-61");

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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(demoStoreNoticeDismiss));
        driver.findElement(pilateGroup).click();
        driver.findElement(product).click();
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void windowHandlesTest() {
        By addToWishlist = By.cssSelector("a.add_to_wishlist");
        driver.findElement(addToWishlist).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(addToWishlist));
        By wishlistLink = By.cssSelector("li#menu-item-248");
        driver.findElement(wishlistLink).click();

        String parentWindow = driver.getWindowHandle();
        Set<String> window = driver.getWindowHandles();
        window.remove(parentWindow);
        String wishlistWindow = window.iterator().next();
        driver.switchTo().window(wishlistWindow);

        By removeFromWishlist = By.cssSelector("a.remove_from_wishlist");
        driver.findElement(removeFromWishlist).click();
        By emptyWishlist = By.cssSelector("td.wishlist-empty");
        wait.until(ExpectedConditions.presenceOfElementLocated(emptyWishlist));
        Assertions.assertDoesNotThrow(()->wait.until(ExpectedConditions.presenceOfElementLocated(emptyWishlist)), "Wishlist is not empty.");
    }
}
