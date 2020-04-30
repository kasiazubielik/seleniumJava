package OtherMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SelectElements2 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        driver.navigate().to("https://www.w3schools.com/code/tryit.asp?filename=FZKB5XSEVTU8");
        driver.findElement(By.cssSelector("button.w3-bar-item")).click();
        driver.switchTo().frame("iframeResult");
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void selectElement() {
        WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body>select")));
        Select dropdown = new Select(dropdownElement);
        Boolean isMultiple = dropdown.isMultiple();
        dropdown.selectByIndex(1);
        dropdown.selectByIndex(4);
        dropdown.selectByValue("opcja_3");
        dropdown.selectByVisibleText("Opcja czwarta");

        dropdown.deselectByIndex(4);
        dropdown.deselectByValue("opcja_2");
        dropdown.deselectAll();
    }
}
