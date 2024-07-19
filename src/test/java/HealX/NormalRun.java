package HealX;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class NormalRun {
    private static WebDriver driver;
    public static Base base;
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    public WebElement getElement(String locatorName){
        RemoteDB db = new RemoteDB();
        String locatorValue= db.getLocator(locatorName) ;
        String locatorType = db.getLocatorType(locatorName);
        System.out.println("Rahul :"+ locatorType+",dsnfsdn");
        WebElement element = base.findElement(locatorType,locatorValue);
        if (element == null) {
            driver.navigate().refresh();
            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
            element = base.findElement(locatorType, locatorValue);
            if (element != null) {
                return element;
            }
            String alternateLocator = db.getAlternateLocator(locatorName);
            String alternateLocatorType = db.getAlternateLocatorType(locatorName);
            if (!alternateLocator.equals("")) {
                element = base.findElement(alternateLocatorType, alternateLocator);
                if(element != null){
                    return element;
                }
            }
            HealX healingObj = new HealX(driver);


        }
        return element;
    }
    public static void main(String[] args) {
        base = new Base();
        base.setUp();
        driver = base.getDriver();
        NormalRun nr = new NormalRun();
        WebElement ele = nr.getElement("logo");

        base.tearDown();
    }
}