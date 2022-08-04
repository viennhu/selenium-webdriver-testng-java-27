package webdriver;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_21_Wait_PageReady {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("MAC OS")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        }
        driver = new FirefoxDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Practice_01() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        Assert.assertTrue(isPageLoadedSuccess());
    }

    @Test
    public void TC_02_Practice_02() {
        driver.get("https://blog.testproject.io/");
        if (driver.findElement(By.className("mailch-wrap")).isDisplayed()) {
            driver.findElement(By.id("close-mailch")).click();
        } {
            action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
            Assert.assertTrue(isPageLoadedSuccess());
            driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
            driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Search Results']")));
            Assert.assertTrue(isPageLoadedSuccess());
            List<WebElement> allTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
            for (WebElement title : allTitles) {
                System.out.println(title.getText());
                Assert.assertTrue(title.getText().contains("Selenium"));

            }
        }
    }

    @Test
    public void TC_03_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(99999);
    }

    public boolean isPageLoadedSuccess() {
        explicitWait = new WebDriverWait(driver, 30);
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active ===0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jQueryLoad);
    }

}

