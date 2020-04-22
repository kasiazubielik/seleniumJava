package Exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InteracringWithWebElementsEx8 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void existingUsernameCorrectPasswordTest() {
        String login = "kasia";
        String password = "Nastraganiewdzientargowy";
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String userDisplayedName = "kasia";
        String myAccountContent = driver.findElement(By.cssSelector("div.woocommerce-MyAccount-content")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayedName),
                "My account page does not contain correct name. Expected name: " + userDisplayedName +
                        " was not found in a string: " + myAccountContent);
    }

    @Test
    public void existingEmailCorrectPasswordTest() {
        String login = "kasia@fakemail.pl";
        String password = "Nastraganiewdzientargowy";
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String userDisplayedName = "kasia";
        String myAccountContent = driver.findElement(By.cssSelector("div.woocommerce-MyAccount-content")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayedName),
                "My account page does not contain correct name. Expected name: " + userDisplayedName +
                " was not found in a string: " + myAccountContent);
    }

    @Test
    public void unexistingUsernameCorrectPasswordTest() {
        String login = "kasiaaaa";
        String password = "Nastraganiewdzientargowy";
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String errorMessageText = driver.findElement(By.cssSelector("ul.woocommerce-error")).getText();
        String expectedErrorMessage = "Nieznany użytkownik. Proszę spróbować ponownie lub użyć adresu email.";
        Assertions.assertEquals(expectedErrorMessage, errorMessageText, "Error message is not correct");
    }

    @Test
    public void unexistingEmailCorrectPasswordTest() {
        String login = "kasiaaaa@fakemail.pl";
        String password = "Nastraganiewdzientargowy";
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String errorMessageText = driver.findElement(By.cssSelector("ul.woocommerce-error")).getText();
        String expectedErrorMessage = "Nieznany adres email. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.";
        Assertions.assertEquals(expectedErrorMessage, errorMessageText, "Error message is not correct");
    }

    @Test
    public void existingUsernameInorrectPasswordTest() {
        String login = "kasia";
        String password = "Sezamieotworzsie";
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String errorMessageText = driver.findElement(By.cssSelector("ul.woocommerce-error")).getText();
        String expectedErrorMessage = "Błąd: Wprowadzone hasło dla użytkownika " +  login + " jest niepoprawne. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedErrorMessage, errorMessageText, "Error message is not correct");
    }

    @Test
    public void existingEmailInorrectPasswordTest() {
        String login = "kasia@fakemail.pl";
        String password = "Sezamieotworzsie";
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String errorMessageText = driver.findElement(By.cssSelector("ul.woocommerce-error")).getText();
        String expectedErrorMessage = "BŁĄD: Dla adresu email " + login + " podano nieprawidłowe hasło. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedErrorMessage, errorMessageText, "Error message is not correct");
    }

    @Test
    public void existingUsernameUnexistingPasswordTest() {
        String login = "kasia";
        String password = "";
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String errorMessageText = driver.findElement(By.cssSelector("ul.woocommerce-error")).getText();
        String expectedErrorMessage = "Błąd: Hasło jest puste.";
        Assertions.assertEquals(expectedErrorMessage, errorMessageText, "Error message is not correct");
    }

    @Test
    public void unexistingUsernameDummyPasswordTest() {
        String login = "";
        String password = "cokolwiek";
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String errorMessageText = driver.findElement(By.cssSelector("ul.woocommerce-error")).getText();
        String expectedErrorMessage = "Błąd: Nazwa użytkownika jest wymagana.";
        Assertions.assertEquals(expectedErrorMessage, errorMessageText, "Error message is not correct");
    }
}
