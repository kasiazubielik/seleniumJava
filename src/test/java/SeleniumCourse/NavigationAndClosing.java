package SeleniumCourse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class NavigationAndClosing {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void getMethod() {
        driver.get("https://google.pl");
    }

    @Test
    public void navigate() {
        driver.navigate().to("https://google.pl");
        driver.navigate().to("https://www.amazon.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
    }

    @Test
    public void navigateURL() {
        URL googleURL = null;
        try {
            googleURL = new URL("https://google.pl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.navigate().to(googleURL);
    }
}
