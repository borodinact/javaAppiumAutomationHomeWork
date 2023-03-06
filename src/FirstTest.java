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
import java.util.ArrayList;

public class FirstTest {
    private static final String errMesWebElement = "Cannot find webElement by locator ";
    private ArrayList articles;
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
     * тест, который:
     *
     * 1. Ищет какое-то слово
     * 2. Убеждается, что найдено несколько статей
     * 3. Отменяет поиск
     * 4. Убеждается, что результат поиска пропал
     */
    @Test
    public void testSearchSomeArticles(){
        waitForElementPresentAndClick(By.className("android.widget.TextView"));
        waitForElementPresentAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "keys");
        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"));
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_close_btn"));
        Assert.assertTrue(waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list")));
    }

    /**
     * функция, которая проверяет наличие ожидаемого текста у элемента
     */
    private void assertElementHasText(By by, String expectedText, String errorMessage){
        String actualText= waitForElementPresent(by).getText();
        Assert.assertEquals(errorMessage, expectedText, actualText);
    }

    private WebElement waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errMesWebElement + by);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private boolean waitForElementNotPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errMesWebElement + by);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementPresentAndClick(By by) {
        WebElement e = waitForElementPresent(by);
        e.click();
        return e;
    }

    private WebElement waitForElementPresentAndSendKeys(By by, String keys) {
        WebElement e = waitForElementPresent(by);
        e.sendKeys(keys);
        return e;
    }
}
