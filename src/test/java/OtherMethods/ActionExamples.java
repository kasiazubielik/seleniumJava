package OtherMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionExamples {

    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions = new Actions(driver);
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void clickExample() {
        driver.navigate().to("https://jqueryui.com/selectable/#default");
        actions.moveByOffset(488, 380).click().build().perform();
        driver.switchTo().frame(0);
        List<WebElement> listElements = driver.findElements(By.cssSelector("#selectable>li"));
        WebElement secondElement = listElements.get(1);
        actions.click(secondElement).build().perform();
    }

    @Test
    public void doubleClickExample() {
        driver.navigate().to("https://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
        actions.moveByOffset(330, 172).doubleClick().build().perform();
        driver.navigate().refresh();
        WebElement box = driver.findElement(By.cssSelector("#box"));
        actions.doubleClick(box).build().perform();
    }

    @Test
    public void contextClick() {
        driver.navigate().to("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement editContextMenu = driver.findElement(By.cssSelector(".context-menu-icon-edit"));
//        actions.moveByOffset(461,195).contextClick().build().perform();
        WebElement contextButton = driver.findElement(By.cssSelector(".context-menu-one"));
        actions.contextClick(contextButton).click(editContextMenu).build().perform();
    }
}
