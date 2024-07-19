package HealX;

import net.bytebuddy.description.type.TypeDescription;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.lang3.tuple.Pair;


import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class FirstRun {
    private static WebDriver driver;
    public static Base base;
    //    public void
    public Map<String, String> getElementAttributes(WebElement element) {
        Map<String, String> attributes = null;
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement element = (WebElement) js.executeScript(
//                "return document.elementFromPoint(arguments[0], arguments[1]);", x, y
//        );

        if (element != null) {
            // Get all attributes of the element
            attributes = new HashMap<>();
            String script = "var items = {}; " +
                    "for (index = 0; index < arguments[0].attributes.length; ++index) { " +
                    "items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; " +
                    "return items;";
            attributes = (Map<String, String>) js.executeScript(script, element);
            attributes.forEach((key, value) -> {
                System.out.println(key + ": " + value);
            });
        } else {
            System.out.println("No element found at the given coordinates.");
        }
        return attributes;
    }
    public Pair<Integer, Integer> getCoordinates(WebElement element){
        Point point = element.getLocation();

        int x = point.getX();
        int y = point.getY();
        // Assert the element is not null and the position is retrieved
        assertNotNull(element);
        assertNotNull(point);
        // Output the position
        System.out.println("Element's Position: (" + x + ", " + y + ")");
        return Pair.of(x,y);
    }

    public void firstRunUpdate(String locatorName, String locatorType,String locatorValue) {
        // Locate the element
        RemoteDB db = new RemoteDB();
        String locator= db.getLocator(locatorName) ;
        if(locator == null){
            db.addData(locatorName,locatorType,locatorValue);
        }
        WebElement element = base.findElement(locatorType,locatorValue);
        Pair<Integer, Integer> coordinates = getCoordinates(element);
        int x = coordinates.getLeft();
        int y = coordinates.getRight();
        Map<String, String> attributes = getElementAttributes(element);

        db.setAttributesAndCoordinates(locatorName,x,y,attributes);

    }

    public static void main(String[] args) {
        base = new Base();
        base.setUp();
        driver = base.getDriver();
        FirstRun fr = new FirstRun();
        fr.firstRunUpdate("logo","id","nav-logo-spritesd");

        base.tearDown();
    }
}



//    Element's Position: (2, 5)
//    Element's Position: (138, 25)
//    Element's Position: (156, 15)
//
//    Process finished with exit code 0
