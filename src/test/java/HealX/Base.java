package HealX;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
    public static WebDriver driver;

    Base() {
        driver = new ChromeDriver();
    }

    public void setUp() {
        driver.get("https://www.amazon.in/");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebElement findElement(String locatorType, String locatorValue) {
        WebElement element = null;
        try {
            if (locatorType.equals("id")) {
                element = driver.findElement(By.id(locatorValue));
            } else if (locatorType == "css") {
                element = driver.findElement(By.cssSelector(locatorValue));
            } else if (locatorType == "xpath") {
                element = driver.findElement(By.xpath(locatorValue));
            } else {
                element = driver.findElement(By.xpath("//*[contains(@" + locatorType + ", '" + locatorValue + "')]"));
            }
        } catch (NoSuchElementException e) {
            return null;
        }

        return element;
    }
}
