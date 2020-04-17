package DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GettingURLSourceAndTitle {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void getCurrentURLExample() {
        String googleURL = "https://www.google.pl/";
        driver.navigate().to("https://google.pl");
        Assertions.assertEquals(googleURL, driver.getCurrentUrl(), "Current URL is not: " + googleURL);
    }

    @Test
    public void getTitleExample() {
        String googleTitle = "Google";
        driver.navigate().to("https://google.pl");
        Assertions.assertEquals(googleTitle, driver.getTitle(), "Page title is not: " + googleTitle);
    }

    @Test
    public void getPageSourceExample() {
        String googleImage = "/logos/doodles/2020/thank-you-doctors-nurses-and-medical-workers-6753651837108754.4-law.gif";
        driver.navigate().to("https://google.pl");
        Assertions.assertTrue(driver.getPageSource().contains(googleImage), "Page source does not contain: " + googleImage);
    }
}
