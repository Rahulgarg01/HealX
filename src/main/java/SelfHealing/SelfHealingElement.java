package SelfHealing;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v118.audits.model.StyleSheetLoadingIssueReason;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelfHealingElement extends Details implements WebElement,WrapsElement{
    private WebElement originalElement;
    private ElementLocator locator;
    private WebDriver driver;
    private Field field;
    private String locatorName;
    FirstRun objectFirstRun;

    public SelfHealingElement(WebElement element, ElementLocator locator, WebDriver driver, Field field) {
        System.out.println("SelfHealingElement");
        this.originalElement = element;
        this.locator = locator;
        this.driver = driver;
        System.out.println(driver instanceof WebDriver);
        System.out.println(driver instanceof CustomWebDriver);
        System.out.println(driver instanceof FirstRunDriver);
        objectFirstRun = new FirstRun(driver);
        this.field = field;
        this.locatorName = field.getName();
        System.out.println(locatorName);
    }

    private WebElement healLocator() {
        // Implement your self-healing logic here
        // This method should return a new WebElement based on your healing strategy
        // For example:
        // return driver.findElement(By.xpath("//healedXpath"));
        System.out.println("Healing the FindBy locator");
        if (locatorName!=null){
            NormalRun nr = new NormalRun(driver);
            WebElement healedElement = nr.getElement(locatorName);
            if (healedElement != null) {
                return healedElement;
            }
        }
//        NormalRun nr = new NormalRun(driver);
//        WebElement healedElement = nr.getElement(locatorName,by);
//        if (healedElement != null) {
//            return healedElement;
//        }
        return null;
    }
    private WebElement getElement() {
        try {
            System.out.println("Rammmmmm"+originalElement);
            return originalElement;
        } catch (Exception e) {
            return healLocator();
        }
    }
    //    @Override
//    public String toString() {
//        return originalElement.toString(); // or a custom message that includes locator info
//    }
    @Override
    public WebElement getWrappedElement() {
        return originalElement;
    }

    @Override
    public void click() {
        if (driver instanceof FirstRunDriver) {
            getElement().click();
            WebElement ele = getElement();
            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
        }else {
            try {
                WebElement ele = getElement();
                ele.click();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                healLocator().click();
            }
        }
    }

    @Override
    public void submit() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();
            ele.submit();
            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
        }else {
            try {
                WebElement ele = getElement();
                ele.submit();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                healLocator().submit();
            }
        }
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();
            ele.sendKeys(keysToSend);
            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
        }else {
            try {
                WebElement ele = getElement();
                ele.sendKeys(keysToSend);
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                healLocator().sendKeys(keysToSend);
            }
        }
    }

    @Override
    public void clear() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();
            ele.clear();
            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
        }else {
            try {
                WebElement ele = getElement();
                ele.clear();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                healLocator().clear();
            }
        }
    }

    @Override
    public String getTagName() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.getTagName();
        }else {
            try {
                WebElement ele = getElement();
                return ele.getTagName();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().getTagName();
            }
        }
    }

    @Override
    public String getAttribute(String name) {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.getAttribute(name);
        }else {
            try {
                WebElement ele = getElement();
                return ele.getAttribute(name);
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().getAttribute(name);
            }
        }
    }

    @Override
    public boolean isSelected() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.isSelected();
        }else {
            try {
                WebElement ele = getElement();
                return ele.isSelected();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().isSelected();
            }
        }
    }

    @Override
    public boolean isEnabled() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.isEnabled();
        }else {
            try {
                WebElement ele = getElement();
                return ele.isEnabled();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().isEnabled();
            }
        }
    }

    @Override
    public String getText() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.getText();
        }else {
            try {
                WebElement ele = getElement();
                return ele.getText();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().getText();
            }
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.findElements(by);
        }else {
            try {
                WebElement ele = getElement();
                return ele.findElements(by);
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().findElements(by);
            }
        }
    }

    @Override
    public WebElement findElement(By by) {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.findElement(by);
        }else {
            try {
                WebElement ele = getElement();
                return ele.findElement(by);
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().findElement(by);
            }
        }
    }

    @Override
    public boolean isDisplayed() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.isDisplayed();
        }else {
            try {
                WebElement ele = getElement();
                return ele.isDisplayed();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().isDisplayed();
            }
        }
    }

    @Override
    public Point getLocation() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.getLocation();
        }else {
            try {
                WebElement ele = getElement();
                return ele.getLocation();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().getLocation();
            }
        }
    }

    @Override
    public Dimension getSize() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.getSize();
        }else {
            try {
                WebElement ele = getElement();
                return ele.getSize();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().getSize();
            }
        }
    }

    @Override
    public Rectangle getRect() {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.getRect();
        }else {
            try {
                WebElement ele = getElement();
                return ele.getRect();
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().getRect();
            }
        }
    }

    @Override
    public String getCssValue(String propertyName) {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.getCssValue(propertyName);
        }else {
            try {
                WebElement ele = getElement();
                return ele.getCssValue(propertyName);
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().getCssValue(propertyName);
            }
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        if (driver instanceof FirstRunDriver) {
            WebElement ele = getElement();

            String regex = "->\\s*(\\w[\\w\\s]*):\\s*(.+)]";
            System.out.println("Element: "+ele);

            Pattern pattern = Pattern.compile(regex);
            String locatorString = originalElement.toString();
            Matcher matcher = pattern.matcher(locatorString);
            if (matcher.find()) {
                String locatorType = matcher.group(1);
                String locatorValue = matcher.group(2);
                objectFirstRun.firstRunUpdate(locatorName, locatorType, locatorValue, ele);
            }
            return ele.getScreenshotAs(target);
        }else {
            try {
                WebElement ele = getElement();
                return ele.getScreenshotAs(target);
//        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            } catch (Exception e) {
                System.out.println("@FindBy Healing start ");
                return healLocator().getScreenshotAs(target);
            }
        }
    }

}