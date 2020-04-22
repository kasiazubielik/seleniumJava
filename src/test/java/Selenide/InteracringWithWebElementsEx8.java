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
    public void existingUsernameCorrectPasswordTest() {
        String login = "kasia";
        String password = "Nastraganiewdzientargowy";
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();

        String userDisplayedName = "kasia";
        SelenideElement myAccountContent = $("div.woocommerce-MyAccount-content");
        myAccountContent.shouldHave(text(userDisplayedName));
    }

    @Test
    public void existingEmailCorrectPasswordTest() {
        String login = "kasia@fakemail.pl";
        String password = "Nastraganiewdzientargowy";
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();

        String userDisplayedName = "kasia";
        SelenideElement myAccountContent = $("div.woocommerce-MyAccount-content");
        myAccountContent.shouldHave(text(userDisplayedName));
    }

    @Test
    public void unexistingUsernameCorrectPasswordTest() {
        String login = "kasiaaaa";
        String password = "Nastraganiewdzientargowy";
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();

        SelenideElement errorMessageText = $("ul.woocommerce-error");
        String expectedErrorMessage = "Nieznany użytkownik. Proszę spróbować ponownie lub użyć adresu email.";
        errorMessageText.shouldHave(text(expectedErrorMessage));
    }

    @Test
    public void unexistingEmailCorrectPasswordTest() {
        String login = "kasiaaaa@fakemail.pl";
        String password = "Nastraganiewdzientargowy";
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();

        SelenideElement errorMessageText = $("ul.woocommerce-error");
        String expectedErrorMessage = "Nieznany adres email. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.";
        errorMessageText.shouldHave(text(expectedErrorMessage));
    }

    @Test
    public void existingUsernameInorrectPasswordTest() {
        String login = "kasia";
        String password = "Sezamieotworzsie";
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();

        SelenideElement errorMessageText = $("ul.woocommerce-error");
        String expectedErrorMessage = "Błąd: Wprowadzone hasło dla użytkownika " +  login + " jest niepoprawne. Nie pamiętasz hasła?";
        errorMessageText.shouldHave(text(expectedErrorMessage));
    }

    @Test
    public void existingEmailInorrectPasswordTest() {
        String login = "kasia@fakemail.pl";
        String password = "Sezamieotworzsie";
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();

        SelenideElement errorMessageText = $("ul.woocommerce-error");
        String expectedErrorMessage = "BŁĄD: Dla adresu email " + login + " podano nieprawidłowe hasło. Nie pamiętasz hasła?";
        errorMessageText.shouldHave(text(expectedErrorMessage));
    }

    @Test
    public void existingUsernameUnexistingPasswordTest() {
        String login = "kasia";
        String password = "";
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();

        SelenideElement errorMessageText = $("ul.woocommerce-error");
        String expectedErrorMessage = "Błąd: Hasło jest puste.";
        errorMessageText.shouldHave(text(expectedErrorMessage));
    }

    @Test
    public void unexistingUsernameDummyPasswordTest() {
        String login = "";
        String password = "cokolwiek";
        $("input#username").setValue(login);
        $("input#password").setValue(password);
        $("a.woocommerce-store-notice__dismiss-link").click();
        $("button[name='login']").click();

        SelenideElement errorMessageText = $("ul.woocommerce-error");
        String expectedErrorMessage = "Błąd: Nazwa użytkownika jest wymagana.";
        errorMessageText.shouldHave(text(expectedErrorMessage));
    }
}
