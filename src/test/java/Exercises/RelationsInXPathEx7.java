package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class RelationsInXPathEx7 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-xpath/");
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
    public void relationsInXPathTest() {
        driver.findElement(By.xpath(".//thead/tr[2]/td[2]"));
        driver.findElement(By.xpath(".//strong[text()='Nabywca:']/.."));
        driver.findElement(By.xpath(".//strong[text()='Nabywca:']/parent::*"));

        driver.findElement(By.xpath(".//td[text()='Bloczek samoprzylepny']/../td[2]"));
        driver.findElement(By.xpath(".//td[text()='Bloczek samoprzylepny']/following-sibling::td[1]"));
        driver.findElement(By.xpath(".//tbody/tr[2]/td[2]"));

        driver.findElement(By.xpath(".//td[text()='Bloczek samoprzylepny']/../td[3]"));
        driver.findElement(By.xpath(".//td[text()='Bloczek samoprzylepny']/following-sibling::td[2]"));
        driver.findElement(By.xpath(".//tbody/tr[2]/td[3]"));

        driver.findElement(By.xpath(".//td[text()='Bloczek samoprzylepny']/../td[4]"));
        driver.findElement(By.xpath(".//td[text()='Bloczek samoprzylepny']/following-sibling::td[3]"));
        driver.findElement(By.xpath(".//tbody/tr[2]/td[4]"));

    }
}
