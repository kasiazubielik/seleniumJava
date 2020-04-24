package DriverMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MoreMethodsOnElements {

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

    @Test
    public void infoElementHeader() {

        WebElement element = driver.findElement(By.cssSelector("#masthead"));
        String text = element.getText();
        String attibute = element.getAttribute("role");
        String cssValue = element.getCssValue("background-color");
        String tag = element.getTagName();
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Rectangle locationAndSize = element.getRect();
        boolean isDisplayed = element.isDisplayed();
        boolean isSelected = element.isSelected();
        boolean isEnabled = element.isEnabled();
    }

    @Test
    public void infoElementButton() {

        WebElement element = driver.findElement(By.cssSelector("button[name='update_cart']"));
        String text = element.getText();
        String attibute = element.getAttribute("role");
        String cssValue = element.getCssValue("background-color");
        String tag = element.getTagName();
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Rectangle locationAndSize = element.getRect();
        boolean isDisplayed = element.isDisplayed();
        boolean isSelected = element.isSelected();
        boolean isEnabled = element.isEnabled();
    }
}
