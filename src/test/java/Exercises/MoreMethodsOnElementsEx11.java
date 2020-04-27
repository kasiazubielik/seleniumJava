package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class MoreMethodsOnElementsEx11 {

    WebDriver driver;
    By cookieConstantBar = By.cssSelector("p.demo_store>a");

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 760));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/metody-na-elementach/");
        driver.findElement(cookieConstantBar).click();
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void methodsOnElements() {
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement sailingButton = driver.findElement(By.cssSelector("[name='sailing']"));
        List<WebElement> yellowButtons = driver.findElements(By.cssSelector("a.button"));
        WebElement selectedCheckbox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement selectedRadiobutton = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement notSelectedCheckbox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement notSelectedRadiobutton = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));
        List<WebElement> elementsWithButtonClass = driver.findElements(By.cssSelector(".button"));

        assertAll("Checking properties of elements",
                ()->assertFalse(mainPageButton.isEnabled(), "'Main Page' button is not disabled."),
                ()->assertFalse(sailingButton.isDisplayed(), "'Sailing' button is displayed."),
                ()->assertThatButtonsAreYellow(yellowButtons),
                ()->assertTrue(selectedCheckbox.isSelected(), "Checkbox is not selected."),
                ()->assertTrue(selectedRadiobutton.isSelected(), "Radiobutton is not selected."),
                ()->assertFalse(notSelectedCheckbox.isSelected(), "Checkbox is selected."),
                ()->assertFalse(notSelectedRadiobutton.isSelected(), "Checkbox is selected."),
                ()->assertThatElementsHaveCorrectTagName(elementsWithButtonClass)
        );
    }

    private void assertThatButtonsAreYellow(List<WebElement> buttons) {
        for (WebElement button:buttons) {
            String color = button.getCssValue("background-color");
            Assertions.assertEquals("rgba(245, 233, 101, 1)", color, "Button has different color than expected");
        }
    }

    private void assertThatElementsHaveCorrectTagName(List<WebElement> elementsWithButtonClass) {
        for (WebElement element:elementsWithButtonClass) {
            Assertions.assertEquals("a", element.getTagName(), "Elements tag is not 'a'");
        }
    }
}
