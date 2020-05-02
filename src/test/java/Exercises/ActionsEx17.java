package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionsEx17 {

    WebDriver driver;
    Actions actions;
    By demoStoreNoticeDismiss = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/actions/");
        driver.findElement(demoStoreNoticeDismiss).click();
        actions = new Actions(driver);
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void cartContextMenuTest() {
        WebElement menu = driver.findElement(By.cssSelector("#menu-link"));
        WebElement cart = driver.findElement(By.cssSelector("li.menu-cart>a"));
        actions.contextClick(menu).click(cart).build().perform();

        WebElement cartPage = driver.findElement(By.cssSelector("li#menu-item-200"));
        Assertions.assertTrue(cartPage.getAttribute("class").contains("current-menu-item"), "Actual page is not whan expected");
    }

    @Test
    public void doubleClickTest() {
        WebElement colorBox = driver.findElement(By.cssSelector("#double-click"));
        actions.doubleClick(colorBox).build().perform();
        Assertions.assertEquals("rgba(245, 93, 122, 1)", colorBox.getCssValue("background-color"), "Box has different color than expected");
    }

    @Test
    public void sendKeyTest() {
        WebElement input = driver.findElement(By.cssSelector("#input"));
        WebElement confirmButton = driver.findElement(By.cssSelector("button[onclick='zatwierdzTekst()']"));
        actions.sendKeys(input, "mały miś").build().perform();
        actions.click(confirmButton).build().perform();
        String output = driver.findElement(By.cssSelector("p#output")).getText();
        Assertions.assertEquals("Wprowadzony tekst: mały miś", output, "Message is not what expected.");
    }

    @Test
    public void pressingKeysTest() {
        List<WebElement> list = driver.findElements(By.cssSelector("li.ui-selectee"));
        actions.keyDown(Keys.CONTROL).click(list.get(0)).click(list.get(4)).click(list.get(6)).build().perform();
        Assertions.assertAll(
                ()->Assertions.assertTrue(list.get(0).getAttribute("class").contains("ui-selected"), "Item with id=0 was not selected"),
                ()->Assertions.assertTrue(list.get(5).getAttribute("class").contains("ui-selected"), "Item with id=4 was not selected"),
                ()->Assertions.assertTrue(list.get(6).getAttribute("class").contains("ui-selected"), "Item with id=6 was not selected")
        );
    }
}

