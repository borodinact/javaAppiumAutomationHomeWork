import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    //имя метода не менять
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/kr1vet0chka/Documents/Developer/JavaAppiumAutomation/javaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    /**
     * тест проверяет, что поле ввода для поиска статьи содержит текст
     */
    @Test
    public void testSearchFieldText() {
        assertElementHasText(
                By.className("android.widget.TextView"),
                "Поиск по Википедии",
                "Cannot find expected text 'Поиск по Википедии'"
                );
    }

    /**
     * функция, которая проверяет наличие ожидаемого текста у элемента
     */
    private void assertElementHasText(By by, String expectedText, String errorMessage){
        String actualText= waitForElementPresent(by).getText();
        Assert.assertEquals(errorMessage, expectedText, actualText);
    }

    private WebElement waitForElementPresent(By by){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage("Cannot find webElement by locator " + by);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
