package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class Page extends Details {
    FirstRunDriver driver;
    WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(Page.class);
    By RelLogo = By.cssSelector("img[class='img-fluid logo']");
    By searchBar = By.cssSelector("div[class='search-wrapper-cs'] a[aria-label='Search']");

//    @FindBy(css = "img[class='img-fluid logo']")
//    WebElement RelLogo;
//    @FindBy(css = "div[class='search-wrapper-cs'] a[aria-label='Search']")
//    WebElement searchBar;
//    public Page(){
//    }
//
//    public Page(FirstRunDriver driver, WebDriverWait wait){
//        this.driver = driver;
//        this.wait = wait;
////        PageFactory.initElements(driver, this);
//
//    }\
public String getLocatorName(By locator) {
    Class<?> clazz = this.getClass();
    while (clazz != null) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.get(this).equals(locator)) {
                    return ( field.getName());
                    // Add more cases if you use other By types
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        clazz = clazz.getSuperclass(); // Move to the superclass to check fields there
    }
    return null;
}
    public void initialize(FirstRunDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public void validateHomePage(){
//        wait.until(ExpectedConditions.visibilityOf(RelLogo));
//        RelLogo.isDisplayed();
        driver.findElement(RelLogo).isDisplayed();
    }

    public void clickBtn(){
//        wait.until(ExpectedConditions.visibilityOf(searchBar));
//        searchBar.click();
        driver.findElement(searchBar).click();
    }
}
