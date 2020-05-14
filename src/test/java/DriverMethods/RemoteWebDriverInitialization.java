package DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RemoteWebDriverInitialization {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() throws MalformedURLException {

        OperaOptions options = new OperaOptions();
//        options.setCapability(CapabilityType.VERSION, "66");
        options.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        options.setBinary("/usr/bin/opera");

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("http://wikipedia.pl");
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
    public void findingElementById() {
        driver.findElement(By.id("searchInput"));
        driver.findElement(By.name("search"));
        driver.findElement(By.className("searchButton"));

        List<WebElement> externalClassElements = driver.findElements(By.className("external"));

        WebElement elementWithTwoClasses = null;

        for (WebElement externalClassElement : externalClassElements) {
            String elementClass = externalClassElement.getAttribute("class");
            if (elementClass.equals("external text")) {
                elementWithTwoClasses = externalClassElement;
            }
        }
        Assertions.assertTrue(elementWithTwoClasses != null, "Element was not found");

        int numberOfImages = driver.findElements(By.tagName("img")).size();

    }

    @Test
    public void findingElementByLinkText() {
        driver.findElement(By.linkText("Wikisłownik"));
        driver.findElement(By.partialLinkText("redagować"));
    }
}
