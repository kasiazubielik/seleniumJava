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
    String userName = "kasia";
    String email = "kasia@fakemail.pl";
    String incorrectUserName = "kasiaaaa";
    String incorrectEmail = "kasiaaa@fakemail.pl";
    String password = "Nastraganiewdzientargowy";
    String incorrectPassword = "Sezamieotwirzsie";
    String userDisplayedName = "kasia";


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
    public void usernamePasswordTest() {
        logIn(userName, password);
        Assertions.assertTrue(getAccountContent().contains(userDisplayedName),
                "My account page does not contain correct name. Expected name: " + userDisplayedName +
                        " was not found in a string: " + getAccountContent());
    }

    @Test
    public void emailPasswordTest() {
        logIn(email, password);
        Assertions.assertTrue(getAccountContent().contains(userDisplayedName),
                "My account page does not contain correct name. Expected name: " + userDisplayedName +
                " was not found in a string: " + getAccountContent());
    }

    @Test
    public void incorrectUsernamePasswordTest() {
        logIn(incorrectUserName, password);
        String expectedErrorMessage = "Nieznany użytkownik. Proszę spróbować ponownie lub użyć adresu email.";
        Assertions.assertEquals(expectedErrorMessage, getErrorMessageText(), "Error message is not correct");
    }

    @Test
    public void incorrectEmailPasswordTest() {
        logIn(incorrectEmail, password);
        String expectedErrorMessage = "Nieznany adres email. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.";
        Assertions.assertEquals(expectedErrorMessage, getErrorMessageText(), "Error message is not correct");
    }

    @Test
    public void usernameIncorrectPasswordTest() {
        logIn(userName, incorrectPassword);
        String expectedErrorMessage = "Błąd: Wprowadzone hasło dla użytkownika " +  userName + " jest niepoprawne. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedErrorMessage, getErrorMessageText(), "Error message is not correct");
    }

    @Test
    public void emailIncorrectPasswordTest() {
        logIn(email, incorrectPassword);
        String expectedErrorMessage = "BŁĄD: Dla adresu email " + email + " podano nieprawidłowe hasło. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedErrorMessage, getErrorMessageText(), "Error message is not correct");
    }

    @Test
    public void emptyUsernamePasswordTest() {
        String login = "";
        logIn(login, password);
        String expectedErrorMessage = "Błąd: Nazwa użytkownika jest wymagana.";
        Assertions.assertEquals(expectedErrorMessage, getErrorMessageText(), "Error message is not correct");
    }

    @Test
    public void usernameEmptyPasswordTest() {
        String password = "";
        logIn(userName, password);
        String expectedErrorMessage = "Błąd: Hasło jest puste.";
        Assertions.assertEquals(expectedErrorMessage, getErrorMessageText(), "Error message is not correct");
    }

    private void logIn(String userName, String password) {
        driver.findElement(By.cssSelector("input#username")).sendKeys(userName);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    private String getAccountContent() {
        return driver.findElement(By.cssSelector("div.woocommerce-MyAccount-content")).getText();
    }

    private String getErrorMessageText() {
        return driver.findElement(By.cssSelector("ul.woocommerce-error")).getText();
    }
}
