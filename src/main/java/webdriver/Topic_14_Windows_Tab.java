package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_14_Windows_Tab {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("MAC OS")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(2);
        String parentID = driver.getWindowHandle();
        switchToWindowUsingID(parentID);
        Assert.assertEquals(driver.getTitle(),"Google");
        String googleID = driver.getWindowHandle();
        switchToWindowUsingID(googleID);
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(2);
        switchToWindowUsingTitle("Facebook");
        Assert.assertTrue(driver.getTitle().contains("Facebook"));
        String facebookID = driver.getWindowHandle();
        switchToWindowUsingTitle("SELENIUM");
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSecond(2);
        switchToWindowUsingTitle("Mua hàng");
        Assert.assertTrue(driver.getTitle().contains("Mua hàng"));
        String tikiID = driver.getWindowHandle();
        closeAllWindowExceptTheParentWindow(parentID);
        switchToWindowUsingID(tikiID);
        sleepInSecond(1);
        Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int generateRandomNumber () {
        Random rand = new Random();
        return rand.nextInt(99999);
    }

    public void switchToWindowUsingID (String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
            }
        }
    }

    public void switchToWindowUsingTitle (String expectedTitle) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            driver.switchTo().window(id);
            System.out.println("======================");
            System.out.println(driver.getTitle());
            if (driver.getTitle().contains(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowExceptTheParentWindow (String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }
}
