package SelfHealing;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;


import java.lang.reflect.Field;

public class SelfHealingDecorator extends DefaultFieldDecorator{
    private WebDriver driver;

    public SelfHealingDecorator(ElementLocatorFactory factory, WebDriver driver) {
        super(factory);
        System.out.println("SelfHealingDecorator");
        this.driver = driver;
    }
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (!WebElement.class.isAssignableFrom(field.getType())) {
            return super.decorate(loader, field);
        }

        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }

        return new SelfHealingElement(proxyForLocator(loader, locator), locator, driver,field);
    }

    @Override
    protected WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        return (WebElement) super.proxyForLocator(loader, locator);
    }
}