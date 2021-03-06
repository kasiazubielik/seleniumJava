package Exercises;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitsParameterizedEx10 {

    WebDriver driver;
    WebDriverWait wait;
    By cookieConstantBar = By.cssSelector("p.demo_store>a");
    By yogaGroup = By.cssSelector("a[href*='product-category/yoga-i-pilates']");
    By product = By.cssSelector("li.post-60");
    By addToCart = By.cssSelector("button[name='add-to-cart']");
    By cartContent = By.cssSelector("a.cart-contents");
    String correctCoupon = "10procent";
    String goToCart = "procent";

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 760));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");

        wait = new WebDriverWait(driver, 15);

        driver.findElement(cookieConstantBar).click();
        driver.findElement(yogaGroup).click();
        driver.findElement(product).click();
        driver.findElement(addToCart).click();
        driver.findElement(cartContent).click();
    }

    @AfterEach
    public void driverQuiteAndClosing() {
        driver.close();
        driver.quit();
    }

    @DisplayName("Simple coupon tests")
    @ParameterizedTest(name = "Coupon \"{0}\"")
    @CsvSource({ "'', Proszę wpisać kod kuponu.",
            "10procent, Kupon został pomyślnie użyty.",
            "procent, Kupon \"procent\" nie istnieje! "})
    void simpleCouponTests(String coupon, String expectedAlert) {
        applyCoupon(coupon);
        Assertions.assertEquals(expectedAlert, getAlertText(), "Alert message is not what expected");
    }

    @Test
    public void removingCouponTest() {
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        By removeButton = By.cssSelector(".woocommerce-remove-coupon");
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
        waitForProcessingEnd();
        Assertions.assertEquals("Kupon został usunięty.", getAlertText(),
                "Alert message is not what expected");
    }

    @Test
    public void couponAlreadyUsedTest() {
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        Assertions.assertEquals("Kupon został zastosowany!", getAlertText(),
                "Alert message is not what expected");
    }

    private void applyCoupon(String coupon) {
        By couponField = By.cssSelector("input#coupon_code");
        By applyCouponButton = By.cssSelector("button[name='apply_coupon']");
        driver.findElement(couponField).sendKeys(coupon);
        driver.findElement(applyCouponButton).click();
    }

    private void waitForProcessingEnd() {
        By processingOverlay = By.cssSelector(".processing");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(processingOverlay, 0));
        wait.until(ExpectedConditions.numberOfElementsToBe(processingOverlay, 0));
    }

    private String getAlertText() {
        By alert = By.cssSelector("[role='alert']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alert)).getText();
    }
}
