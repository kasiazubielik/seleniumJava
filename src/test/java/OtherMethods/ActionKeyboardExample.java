package OtherMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionKeyboardExample {

    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions = new Actions(driver);
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void sendKeysExample() {
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        WebElement login = driver.findElement(By.cssSelector("input#username"));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", login);

        actions.sendKeys(login, Keys.SHIFT, "testowy user").build().perform();
        actions.click(login).sendKeys(Keys.SHIFT, "testowy user").build().perform();
    }

    @Test
    public void pressingKeysExample() {
        driver.navigate().to("https://jqueryui.com/selectable/#default");
        driver.switchTo().frame(0);

        List<WebElement> listItems = driver.findElements(By.cssSelector("li.ui-selectee"));

        actions.keyDown(Keys.CONTROL).click(listItems.get(0)).click(listItems.get(1)).click(listItems.get(2)).keyUp(Keys.CONTROL)
                .build().perform();
    }
}
