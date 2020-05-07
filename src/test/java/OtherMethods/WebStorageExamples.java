package OtherMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebStorageExamples {

    ChromeDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void sessionLocalExample() {
        driver.navigate().to("https://airly.eu/map/pl/#54.34854,18.64966,i3432");
        LocalStorage local = driver.getLocalStorage();
        String value = local.getItem("persist:map");
        int size = local.size();
        Set<String> keys = local.keySet();
        String removedValue = local.removeItem("persist:map");
        local.setItem("spell", "Alohomora!");
        local.clear();
    }

    @Test
    public void sessionStorageExample() {
        driver.navigate().to("https://www.youtube.com/watch?v=EAog1flzb5k");
        SessionStorage session = driver.getSessionStorage();
        String value = session.getItem("yt-remote-session-name");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(a->session.size() == 5);
        int size = session.size();
        Set<String> keys = session.keySet();
        String removedValue = session.removeItem("yt-remote-session-name");
        session.setItem("spell", "Alohomora!");
        session.clear();
    }
}
