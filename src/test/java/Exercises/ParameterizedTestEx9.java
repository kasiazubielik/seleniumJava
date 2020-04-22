package Exercises;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ParameterizedTestEx9 {
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

    @DisplayName("Successful login")
    @ParameterizedTest(name = "User: \"{0}\"")
    @CsvSource({ "kasia",
            "kasia@fakemail.pl"})
    void successfulLogin(String userName) {
        logIn(userName, password);
        Assertions.assertTrue(getAccountContent().contains(userDisplayedName),
                "My account page does not contain correct name. Expected name: " + userDisplayedName +
                        " was not found in a string: " + getAccountContent());
    }

    @DisplayName("Unsuccessful login")
    @ParameterizedTest(name = "User: \"{0}\" with password: {1} ")
    @CsvSource({ "kasiaaaa, Nastraganiewdzientargowy, Nieznany użytkownik. Proszę spróbować ponownie lub użyć adresu email.",
    "kasiaaaa@fakemail.pl, Nastraganiewdzientargowy, Nieznany adres email. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.",
    "kasia, Sezamieotwirzsie, Błąd: Wprowadzone hasło dla użytkownika kasia jest niepoprawne. Nie pamiętasz hasła?",
    "kasia@fakemail.pl, Sezamieotwirzsie, BŁĄD: Dla adresu email kasia@fakemail.pl podano nieprawidłowe hasło. Nie pamiętasz hasła?",
    "'', Sezamieotwirzsie, Błąd: Nazwa użytkownika jest wymagana.",
    "kasia, '', Błąd: Hasło jest puste."})
    void unsuccessfulLogin(String userName, String password, String expectedMessage) {
        logIn(userName, password);
        Assertions.assertEquals(expectedMessage, getErrorMessageText(), "Error message is not correct.");
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
