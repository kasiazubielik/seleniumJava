package OtherMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelectElement {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        driver.navigate().to("https://allegro.pl/");
        driver.manage().addCookie(new Cookie("gdpr_permission_given", "1"));
        driver.navigate().refresh();
    }

    @AfterEach
    public void driverQuite() {
        driver.quit();
    }

    @Test
    public void selectElement() {
        WebElement productCategories = driver.findElement(By.cssSelector("select[data-role='filters-dropdown-toggle']"));
        Select categoriesDropdown = new Select(productCategories);
        categoriesDropdown.selectByIndex(3);
        Boolean isMultiple = categoriesDropdown.isMultiple();
//        categoriesDropdown.selectByValue("/kategoria/dziecko");
//        categoriesDropdown.selectByVisibleText("Dom i ogród");
        List<WebElement> options = categoriesDropdown.getOptions();
        List<WebElement> selectedOptions = categoriesDropdown.getAllSelectedOptions();
        WebElement firstSelectedOption = categoriesDropdown.getFirstSelectedOption();
    }
}
