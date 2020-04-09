package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class URLSourceAndTitleEx2 {

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
    public void URLSourceAndTitleTest() {
        driver.navigate().to("http://wikipedia.pl");
        String expectedPolishURL = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        Assertions.assertTrue(driver.getCurrentUrl().contains("Wikipedia:Strona_g%C5%82%C3%B3wna"), "URL does not contain: " + expectedPolishURL);
        String expectedPolishTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertTrue(driver.getTitle().contains("Wikipedia, wolna encyklopedia"), "Page does not contain: " + expectedPolishTitle);
        String expectedPolishSource = "lang=\"pl\"";
        Assertions.assertTrue(driver.getPageSource().contains(expectedPolishSource), "Source page does not contain: " + expectedPolishSource);

        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        String expectedSpanishURL = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        Assertions.assertEquals(expectedSpanishURL, driver.getCurrentUrl(), "URL is not: " + expectedSpanishURL);
        String expectedSpanishTitle = "Wikipedia, la enciclopedia libre";
        Assertions.assertEquals(expectedSpanishTitle, driver.getTitle(), "Page title is not: " + expectedSpanishTitle);
        String expectedSpanishSource = "lang=\"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(expectedSpanishSource), "Source page does not contain: " + expectedSpanishSource);

    }
}
