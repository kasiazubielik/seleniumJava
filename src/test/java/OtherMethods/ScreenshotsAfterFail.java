package OtherMethods;

import TestHelpers.TestStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class ScreenshotsAfterFail {

    WebDriver driver;

    @RegisterExtension
    TestStatus status = new TestStatus();

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.zooniverse.org/");
    }

    @AfterEach
    public void driverQuite(TestInfo info) throws IOException {
        if (status.isFailed) {
            System.out.println("Test screenshot is available at: " + takeScreenshot(info));
        }
        driver.quit();
    }

    @Test
    public void screenshotExample() {
        driver.findElement(By.cssSelector("button[value='sign-in']")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("hasłotestowe");
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(By.cssSelector("form")).submit();
        WebElement userName = driver.findElement(By.cssSelector("span[class='account-bar'] strong"));
        Assertions.assertEquals("MALAMI2", userName.getText(),
                "Username displayed on header is not correct. The user was probably not logged in.");
    }

    private String takeScreenshot(TestInfo info) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String path = "target/screenshots/" + info.getDisplayName() + " " + formatter.format(timeNow) + ".png";
        FileHandler.copy(screenshot, new File(path));
        return path;
    }
}
