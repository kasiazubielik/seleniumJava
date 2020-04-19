package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RalationsInCSSEx6 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/wyszukiwanie-elementow-poprzez-relacje/");
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
    public void relationsInCSSTest() {
        driver.findElement(By.cssSelector("dd#name-element>input#name"));

        driver.findElement(By.cssSelector("div.second-div>input#email"));

        driver.findElement(By.cssSelector("div.second-div>input:first-of-type"));
        driver.findElement(By.cssSelector("div.second-div>:nth-child(3)"));

        driver.findElement(By.cssSelector("div.second-div input#submit"));

        driver.findElement(By.cssSelector("div.second-div input:last-child"));
        driver.findElement(By.cssSelector("div.second-div>.div>input:last-of-type"));

        driver.findElement(By.cssSelector("div:not(.second-div)>button#submit"));

        driver.findElement(By.cssSelector("div:not([class])>button#submit"));
        driver.findElement(By.cssSelector("p:nth-of-type(2)+div>button#submit"));
        driver.findElement(By.cssSelector("div:not([class])>button:only-of-type"));
    }
}
