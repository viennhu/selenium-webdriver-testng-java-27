package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Custom_Radio_Checkbox {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Custom_Checkbox() {
        driver.get("https://material.angular.io/components/checkbox/examples");
        driver.findElement(By.xpath("//span[text()='Checked']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']//preceding-sibling::span//input")).isSelected());
    }

    @Test
    public void TC_02_Custom_Checkbox_JS() {
        driver.get("https://material.angular.io/components/checkbox/examples");
        jsExecutor.executeScript("arguments[0].click()",driver.findElement(By.xpath("//span[text()='Checked']")));
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']//preceding-sibling::span//input")).isSelected());
    }

    @Test
    public void TC_03_Google_Custom_Checkbox_Radio() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='false']")).isDisplayed());
        driver.findElement(By.cssSelector("div[aria-label='Cần Thơ']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());
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
}
