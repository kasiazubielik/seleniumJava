package DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FileUploadExample {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://gofile.io/?t=uploadFiles");

    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void fileUploadTest() {
        WebElement uploadFileInput = driver.findElement(By.cssSelector("input[type='file']"));
        String expectedFileName = "rakieta.jpeg";
        String expectedFileName2 = "arakieta.jpeg";
        String path = "/home/kasia/Obrazy/" + expectedFileName;
        uploadFileInput.sendKeys(path);

        String actualFileName = driver.findElement(By.cssSelector("tr[id='file_0']>td[class]")).getText();

        Assertions.assertEquals(expectedFileName, actualFileName, "Name of file is different than expected one.");
    }
}
