package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class CookiesEx3 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.navigate().to("http:/wikipedia.pl");
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
    public void cookiesTest() {
        Assertions.assertEquals(4, driver.manage().getCookies().size(), "Number of cookies is not what expected.");

        Cookie newCookie = new Cookie("cookie_test", "value_test", "/");
        driver.manage().addCookie(newCookie);
        Assertions.assertNotNull(newCookie, "Cookie does not exist.");

        driver.manage().deleteCookie(newCookie);
        Assertions.assertNull(driver.manage().getCookieNamed("cookie_test"), "Cookie is not deleted.");

        driver.manage().deleteCookieNamed("WMF-Last-Access");
        Assertions.assertNull(driver.manage().getCookieNamed("WMF-Last-Access"), "Cookie is not deleted.");

        Cookie cookie = driver.manage().getCookieNamed("WMF-Last-Access-Global");
        Assertions.assertEquals(".wikipedia.org", cookie.getDomain(), "Domain is not what expected.");
        Assertions.assertEquals("/", cookie.getPath(), "Path is not what expected.");
        Assertions.assertTrue(cookie.isHttpOnly(), "HttpOnly flag is false.");
    }
}
