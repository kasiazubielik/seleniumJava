package Selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        open("https://gofile.io/?t=uploadFiles");

    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void fileUploadTest() {
        SelenideElement uploadFileInput = $(By.cssSelector("input[type='file']"));
        String fileName = "rakieta.jpeg";
        String fileName2 = "arakieta.jpeg";
        String path = "/home/kasia/Obrazy/" + fileName;
        uploadFileInput.sendKeys(path);

        SelenideElement actualFileName = $(By.cssSelector("tr[id='file_0']>td[class]"));
        actualFileName.shouldHave(text(fileName));
    }
}
