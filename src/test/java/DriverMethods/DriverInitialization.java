package DriverMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.List;

public class DriverInitialization {


    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {

        WebDriverManager.operadriver().setup();

//        System.setProperty("webdriver.opera.driver", "src/main/resources/operadriver");
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.setBinary("/usr/bin/opera");
        driver = new OperaDriver(operaOptions);

//        System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
//        driver = new InternetExplorerDriver();

//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
//        driver = new ChromeDriver();

//        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
//        driver = new FirefoxDriver();

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
