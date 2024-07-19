package HealX;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.internal.Utils.log;

public class HealX {
    public static WebDriver driver;
    public static WebDriverWait wait;
    RemoteDB db = new RemoteDB();
    private static final List<String> ATTRIBUTE_PRIORITY = Arrays.asList(
            "id", "name", "class","type","placeholder","value", "data-test", "aria-label", "title", "for"
    );
    private String getNewNodeAttr(WebElement newNode) {
        log("Getting new node attribute");
        for (String attr : ATTRIBUTE_PRIORITY) {
            String value = newNode.getAttribute(attr);
            if (value != null && !value.isEmpty()) {
                log("Found attribute: " + attr);
                return attr;
            }
        }
        log("Falling back to tagName");
        return "tagName";
    }
    HealX(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public String getHealedLocator() {
        System.out.println("Attempting to find node using attribute priority list");
        for (String attr : ATTRIBUTE_PRIORITY) {
            String val = db.getAttributeValue(attr);
            try {
                log("Trying attribute: " + attr);
                WebElement newNode = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(@" + attr + ", '" + value + "')]")
                ));
                if (newNode != null) {
                    log("Found new node using attribute: " + attr);
                    break;
                }
            } catch (Exception e) {
                log("Failed to find node using attribute: " + attr);
            }
        }

    }
}
