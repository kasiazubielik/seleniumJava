package SeleniumCourse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.GregorianCalendar;
import java.util.Set;

public class Manage {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.navigate().to("https:/www.amazon.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void gettingAndDeletingCookies() {
        driver.manage().deleteCookieNamed("session-id");
        Assertions.assertNull(driver.manage().getCookieNamed("session-id"), "Cookie is not deleted.");
        driver.manage().deleteAllCookies();
        Assertions.assertEquals(0, driver.manage().getCookies().size(), "Number of cookies is not what expected.");

    }

    @Test
    public void addingCookies() {
        Cookie newCookie = new Cookie("test_cookie", "test_value", ".amazon.com", "/", new GregorianCalendar(2020, 04, 12).getTime(), true, true);
        driver.manage().addCookie(newCookie);
        Assertions.assertNotNull(driver.manage().getCookieNamed("test_cookie"), "Cookie does not exist.");
        Cookie secondCookie = new Cookie("test_cookie2", "test_value");
        driver.manage().addCookie(secondCookie);
        Assertions.assertNotNull(driver.manage().getCookieNamed("test_cookie2"), "Cookie does not exist.");
        driver.manage().deleteCookie(newCookie);
        Assertions.assertNull(driver.manage().getCookieNamed("test_cookie"), "Cookie is not deleted.");
    }
}
