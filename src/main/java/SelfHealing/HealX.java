package SelfHealing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.collections.Pair;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.internal.Utils.log;

public class HealX {
    public static WebDriver driver;
    public static WebDriverWait wait;
    RemoteDB db = new RemoteDB();
    private static final List<String> ATTRIBUTE_PRIORITY = Arrays.asList(
            "id", "name", "class","type","placeholder","value", "data-test.java", "aria-label", "href", "title", "for"
    );
    HealX(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public String getHealedLocatorUsingAttributes(String locatorName) {
        System.out.println("Attempting to find node using attribute priority list");
        String newLocator = null;
        for (String attr : ATTRIBUTE_PRIORITY) {
            String value = db.getLocatorAttributeValue(locatorName,attr);
            if(value == null){
                continue;
            }
            try {
                System.out.println("Trying attribute: " + attr);
                newLocator ="[" + attr + "='" + value + "']";
                List<WebElement> newNode = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(newLocator)
                ));
                if(newNode != null && newNode.size() == 1){
                    log("Element Found !!!");
                    return newLocator;
                }
                newLocator = null;
//                if (newNode != null) {
//                    log("Found new node using attribute: " + attr);
//                    break;
//                }
            } catch (Exception e) {
                System.out.println("Failed to find node using attribute: " + attr);
                newLocator = null;
            }
        }
        return newLocator;
    }
    public WebElement getHealedWebElementUsingPosition(String locatorName) {
        WebElement element = null;
        System.out.println("Trying to find Element using it's position on the page");
        Pair<Integer,Integer> coordinates =db.getElementPosition(locatorName);
        if(coordinates!=null && coordinates.first()!=null){
            int x = coordinates.first();
            int y = coordinates.second();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            System.out.println("Rammmmm"+x+" "+y);
            element = (WebElement) js.executeScript(
                "return document.elementFromPoint(arguments[0], arguments[1]);", x, y
            );
        }else{
            System.out.println("Element position not found in the DB");
        }
        return element;
    }
}
