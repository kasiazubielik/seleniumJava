package Selenide;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class InteracringWithWebElementsEx8 {

    String userName = "kasia";
    String email = "kasia@fakemail.pl";
    String incorrectUserName = "kasiaaaa";
    String incorrectEmail = "kasiaaaa@fakemail.pl";
    String password = "Nastraganiewdzientargowy";
    String incorrectPassword = "Sezamieotworzsie";
    String userDisplayedName = "kasia";

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriverRunner.setWebDriver(new ChromeDriver());

        open("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void usernamePasswordTest() {
        logIn(userName, password);
        accountContentAssertion();
    }

    @Test
    public void emailPasswordTest() {
        logIn(email, password);
        accountContentAssertion();
    }

    @Test
    public void incorrectUsernamePasswordTest() {
        logIn(incorrectUserName, password);
        String expectedErrorMessage = "Nieznany użytkownik. Proszę spróbować ponownie lub użyć adresu email.";
        errorMessageAssertion(expectedErrorMessage);
    }

    @Test
    public void incorrectEmailPasswordTest() {
        logIn(incorrectEmail, password);
        String expectedErrorMessage = "Nieznany adres email. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.";
        errorMessageAssertion(expectedErrorMessage);
    }

    @Test
    public void usernameIncorrectPasswordTest() {
        logIn(userName, incorrectPassword);
        String expectedErrorMessage = "Błąd: Wprowadzone hasło dla użytkownika " +  userName + " jest niepoprawne. Nie pamiętasz hasła?";
        errorMessageAssertion(expectedErrorMessage);
    }

    @Test
    public void emailIncorrectPasswordTest() {
        logIn(email, incorrectPassword);
        String expectedErrorMessage = "BŁĄD: Dla adresu email " + email + " podano nieprawidłowe hasło. Nie pamiętasz hasła?";
        errorMessageAssertion(expectedErrorMessage);
    }

    @Test
    public void emptyUsernamePasswordTest() {
        String userName = "";
        logIn(userName, password);
        String expectedErrorMessage = "Błąd: Nazwa użytkownika jest wymagana.";
        errorMessageAssertion(expectedErrorMessage);
    }

    @Test
    public void usernameEmptyPasswordTest() {
        String password = "";
        logIn(userName, password);
        String expectedErrorMessage = "Błąd: Hasło jest puste.";
        errorMessageAssertion(expectedErrorMessage);
    }

    private void logIn(String login, String password) {
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();
    }

    private void errorMessageAssertion(String expectedErrorMessage) {
        SelenideElement getErrorMessage = $("ul.woocommerce-error");
        getErrorMessage.shouldHave(text(expectedErrorMessage));
    }

    private void accountContentAssertion() {
        SelenideElement getAccountContent = $("div.woocommerce-MyAccount-content");
        getAccountContent.shouldHave(text(userDisplayedName));
    }
}
