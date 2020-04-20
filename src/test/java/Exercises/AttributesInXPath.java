package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AttributesInXPath {


    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-atrybuty-w-xpath/");
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
    public void attributesInXPath() {
        // 1. Buttons 1, 2, 5, 6
        driver.findElement(By.xpath(".//*[starts-with(@id, 'button')]"));
        driver.findElement(By.xpath(".//*[text()='Button']"));
        driver.findElement(By.xpath(".//*[contains(@title, 'Button')]"));

        // 2. Buttons 3, 7
        driver.findElement(By.xpath(".//*[contains(@style, 'background-color: #db456f')]"));

        // 3. Buttons 3, 4, 7
        driver.findElement(By.xpath(".//*[contains(@title, 'Btn')]"));
        driver.findElement(By.xpath(".//*[contains(@id, 'btn')]"));
        driver.findElement(By.xpath(".//*[contains(text(), 'Btn')]"));

        // 4. Buttons 1, 2, 5
        driver.findElement(By.xpath(".//*[starts-with(@id, 'button-')]"));

        // 5. Buttons 2, 6, 7
        driver.findElement(By.xpath(".//*[@class='button primary test']"));

        // 6. Buttons 1, 3, 4, 5
        driver.findElement(By.xpath(".//*[contains(@class, 'button accent')]"));

        // 7. Buttons 1, 3, 5
        driver.findElement(By.xpath(".//*[contains(@class, 'button accent') and not(contains(@class, 'test'))]"));
        driver.findElement(By.xpath(".//*[contains(@class, 'button accent') and not(@id='btn-4')]"));

        // 8. Buttons 1, 5
        driver.findElement(By.xpath(".//*[contains(@title, 'Button') and not(contains(@class, 'primary')) ]"));
        driver.findElement(By.xpath(".//*[text()='Button' and contains(@class, 'button accent')]"));

    }
}
