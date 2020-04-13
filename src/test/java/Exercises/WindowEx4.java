package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowEx4 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
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
    public void windowTest() {
        Dimension size = new Dimension(854,480);
        driver.manage().window().setSize(size);

        Point position = new Point(445, 30);
        driver.manage().window().setPosition(position);

        Assertions.assertEquals(size, driver.manage().window().getSize(), "Size of the window is not: (" + size.width + ", " + size.height + ").");
        Assertions.assertEquals(position, driver.manage().window().getPosition(), "Position of the window is not: (" + position.x + ", " + position.y + ").");

        driver.manage().window().maximize();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().window().fullscreen();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
